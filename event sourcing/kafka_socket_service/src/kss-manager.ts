import * as _ from "lodash";
import {Dictionary, each, has, isNil} from "lodash";
import {IConfigurationFeed} from "./interfaces/i-configuration-feed";
import {KSSConsumer} from "./kss-consumer";
import {KSSLogger} from "./kss-logger";
import {ISocketData} from "./interfaces/i-socket-data";
import {Admin, Kafka, logLevel} from "kafkajs";
import {Configuration} from "./kss-config";
import {ITopicName} from "./interfaces/i-topic-name";
import {KSSSocketServer} from "./kss-socket-server";
import {lsof, ProcessInfo} from "list-open-files"

/**
 *  iSOFTBET
 *  Copyright 2019 iSOFTBET
 *  All Rights Reserved.
 *
 *  NOTICE: You may not use, distribute or modify this document without the
 *  written permission of its copyright owner
 *
 *  Created by George Carată on 01/10/19.
 */

/**
 * Manager for the Kafka entities defined in the app.
 */
export class KSSManager {
    public static client: Kafka;
    public static admin: Admin;
    private static _consumers: Dictionary<KSSConsumer> = {};
    private static _consumerCount: number = 0;
    private static _startUpFilesCount = 30;
    private static _maxFilesCount = 0;

    static init(): void {
        const {CompressionTypes, CompressionCodecs} = require('kafkajs');
        CompressionCodecs[CompressionTypes.Snappy] = require('kafkajs-snappy');
        if (_.isNil(KSSManager.client)) {
            KSSManager.client = new Kafka({
                logLevel: logLevel.INFO,
                brokers: Configuration.get("kafkaBrokers"),
                clientId: 'KSSClient',
                // @ts-ignore
                logCreator: KSSLogger.logForward
            });
            this.admin = KSSManager.client.admin();
        }
    }

    /**
     * Injects the configuration consumer, used to redefine the entities within the app in real time.
     */
    static injectConfigurationConsumer() {
        // if (isNil(KSSManager._consumers[TopicNames.CONFIGURATION])) {
        //     KSSManager._consumers[TopicNames.CONFIGURATION] = new KSSConsumer(TopicNames.CONFIGURATION);
        //     TODO remove this hardcode
        //     KSSLogger.instance.log(KSSLogger.level.INFO, 'Configuration consumer is now available.');
        // }
    }

    /**
     * Loads a specific configuration received via configuration topic.
     * @param config The configuration to load.
     */
    static loadConfiguration(config: IConfigurationFeed) {
        KSSManager._consumerCount = config.consumers.length;
        each(config.consumers, (consumerTopic: ITopicName) => {
            KSSManager.addConsumer(consumerTopic, true);
        });
    }

    /*static updateConfiguration(configData: any) {
        KSSLogger.instance.log(KSSLogger.level.INFO, `Updating configuration: ${JSON.stringify(configData)}`);
        if (configData["action"] === "add") {
            KSSManager.addConsumer(configData["topic"]);
        } else if (configData["action"] === "remove") {
            // TODO add removal of topic
        }

    }*/

    /**
     * Adds a [[KSSConsumer]] to the list of entities managed.
     * @param topic The topic the consumer will monitor.
     * @param destroyOldConsumer If true, the old consumer will be destroyed.
     */
    static addConsumer(topic: ITopicName, destroyOldConsumer: boolean = false) {
        if (destroyOldConsumer) {
            if (has(KSSManager._consumers, topic.topicName)) {
                KSSManager._consumers[topic.topicName].disconnectFromKafka();
                KSSManager._consumers[topic.topicName].destroy();
            }
            KSSManager._consumers[topic.topicName] = new KSSConsumer(topic);
            KSSLogger.instance.log(KSSLogger.level.INFO, `Adding consumer for topic: ${topic.topicName}`);
            KSSManager._consumers[topic.topicName].connectToKafka(KSSManager.checkAllConsumersReady);
        } else {
            if (!has(KSSManager._consumers, topic.topicName)) {
                KSSManager._consumers[topic.topicName] = new KSSConsumer(topic);
                KSSLogger.instance.log(KSSLogger.level.INFO, `Adding consumer for topic: ${topic.topicName}`);
                KSSManager._consumers[topic.topicName].connectToKafka(KSSManager.checkAllConsumersReady);
            }
        }
    }

    static checkAllConsumersReady(): void {
        let result = true;
        each(KSSManager._consumers, consumer => {
            if (!consumer.completedInit) {
                result = false;
                return false;
            }
        });
        if (result) {
            KSSLogger.instance.log(KSSLogger.level.INFO, `consumers_ready`);
            require('ulimit')((error: any, result: any) => {
                if (error) {
                    KSSLogger.instance.log(KSSLogger.level.ERROR,
                        "ulimit_error",
                        {
                            event: `Cannot determine ulimit`,
                            result: error
                        }
                    );
                    return;
                }
                KSSManager.trackOpenFiles(result);
                setInterval(() => {
                        const socketCount: number = KSSSocketServer.getSocketCount();
                        if (socketCount > 0) {
                            KSSLogger.instance.log(KSSLogger.level.INFO,
                                "total_socket_count",
                                {totalSocketCount: socketCount}
                            )
                        }
                    },
                    60000);
            })
        }
    }

    /**
     * Getter for a consumer of a specific topic.
     * @param topic The topic monitored.
     */
    static getConsumer(topic: string): KSSConsumer {
        return KSSManager._consumers[topic];
    }

    /**
     * Registers a widget for message forwarding. This will add the filter and check the filtering
     * status.
     * @param socketData This data will be sent by the widgets.
     */
    static registerWidget(socketData: ISocketData) {
        let consumer: KSSConsumer = KSSManager.getConsumer(socketData.data["topic"] as string);
        if (!isNil(consumer)) {
            consumer.addFilter(socketData);
            consumer.checkFilteringStatus();
        }

    }

    /**
     * Destroys the entities of the manager.
     */
    static destroy(): Promise<any> {
        return new Promise<any>((resolve, reject) => {
            let disconnectPromises: Array<Promise<any>> = [];
            each(KSSManager._consumers, (consumer: KSSConsumer) => {
                if (consumer.consumer) {
                    disconnectPromises.push(consumer.consumer.disconnect());
                    // consumer.consumer.disconnect().catch((err: Error) => {
                    //     console.log(err);
                    // });
                }
                // consumer.destroy();
            });
            Promise.all(disconnectPromises)
                .then(() => {
                    each(KSSManager._consumers, (consumer: KSSConsumer) => {
                        consumer.destroy();
                    });
                    KSSManager._consumers = {};
                    resolve();
                })
        })
    }

    private static trackOpenFiles(ulimitResult: any) {
        if (ulimitResult.hasOwnProperty('openFiles') && ulimitResult['openFiles'] !== null) {
            lsof({pids: [process.pid]}).then((result: ProcessInfo[]) => {
                KSSManager._startUpFilesCount = result[0].files.length;
                KSSLogger.instance.log(KSSLogger.level.INFO,
                    "start_up_files_count",
                    {startUpFilesCount: KSSManager._startUpFilesCount}
                )
            }).catch(reason => {
                KSSLogger.instance.log(KSSLogger.level.ERROR,
                    "lsof_error",
                    {
                        result: reason
                    }
                );
            })
            KSSManager._maxFilesCount = ulimitResult['openFiles'];
            KSSLogger.instance.log(KSSLogger.level.INFO,
                "max_files_count",
                {maxFilesCount: KSSManager._maxFilesCount}
            )
            setInterval(() => {
                if (KSSManager._startUpFilesCount + KSSSocketServer.getSocketCount() >= KSSManager._maxFilesCount) {
                    KSSLogger.instance.log(
                        KSSLogger.level.ERROR,
                        "maximum_open_files_error",
                        {
                            event: `Maximum open files limit exceeded!`,
                            total_sockets_count: KSSSocketServer.getSocketCount(),
                            startup_files: KSSManager._startUpFilesCount,
                            max_files: KSSManager._maxFilesCount
                        }
                    )
                }
            }, 60000);
        } else {
            KSSLogger.instance.log(KSSLogger.level.INFO,
                "ulimit_non_numerical",
                {
                    event: `Cannot get ulimit count or openFiles is unlimited`,
                    result: ulimitResult
                })
        }
    }
}
