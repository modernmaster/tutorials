import {ITopicName} from "./interfaces/i-topic-name";
import {Dictionary, each, find, findIndex, get, isEmpty, isNil, remove, setWith, unset} from "lodash";
import {KSSLogger} from "./kss-logger";
import {Observable, PartialObserver, Subject, Subscription} from "rxjs";
import {ISocketData} from "./interfaces/i-socket-data";
import {Consumer} from "kafkajs";
import {KSSManager} from "./kss-manager";
import {Configuration} from "./kss-config";
import {KSSFilterCollection} from "./kss-filter-collection";
import {IFilterMapping} from "./interfaces/i-filter-mapping";

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
 * Creates a consumer group that connects to Kafka, on a specific topic. Uses observables to enable
 * custom filtering of data.
 */
export class KSSConsumer {
    /**
     * Observable for an incoming Kafka message.
     */
    public messageObserver: Observable<unknown>;
    /**
     * Topic monitored.
     */
    private readonly _topic: ITopicName;

    private _completedInit: boolean = false;

    public get completedInit(): boolean {
        return this._completedInit;
    }

    /**
     * Group ID of the consumer.
     */
    private _groupId: string;
    /**
     * Bound handler for the incoming message observable subscription. This is where the data is received.
     */
    private readonly _handleMessageSubscription: PartialObserver<any>;

    /**
     * Reference to the messaging subscription.
     */
    private _subscription: Subscription;
    private messageSubject: Subject<unknown> = new Subject<unknown>();

    /**
     * Instantiates the [[KSSConsumer]] with a specific topic. This is wrapped around the kafka-node
     * NodeJS library (https://www.npmjs.com/package/kafka-node#consumergroup)
     * @param topic The topic to monitor.
     */
    constructor(topic: ITopicName) {
        this._topic = topic;
        this.setGroupID();
        KSSFilterCollection.initializeFilter(this._topic.topicName, this._filters);
        this._handleMessageSubscription = this.messageSubscriptionHandler.bind(this);
        this.addConsumerEvents();
    }

    public connectToKafka(callback: Function) {
        this.initializeConsumer();
        this.connectConsumer(this._topic.topicName, callback);
        this.startLogSocketCount();
    }

    private _consumer: Consumer;

    /**
     * The kafka-node consumer reference.
     */
    get consumer(): Consumer {
        return this._consumer;
    }

    private _filters: Dictionary<any> = {};
    private _sockets: Dictionary<any> = {};

    /**
     * Stores filters used on the incoming messages. The values are socket client instances. The keys
     * are ISB usernames (licensee ID and player ID, joined with a comma)
     */
    get filters(): Dictionary<any> {
        return this._filters;
    }

    private _filtering: boolean = false;

    /**
     * Flag storing if the consumer is actively filtering.
     */
    get filtering(): boolean {
        return this._filtering;
    }

    public setGroupID(): void {
        let groupId = Configuration.get("kafkaGroup");
        if (process.env.HOST && process.env.HOST === "local") {
            groupId += "Local";
        }
        groupId += "_" + this._topic.topicName;
        this._groupId = groupId;
    }

    /**
     * Closes the consumer.
     */
    destroy() {
        // TODO Implement consumer destruction
    }

    disconnectFromKafka() {
        this._consumer.disconnect().then(() => this._completedInit = false);
    }

    /**
     * Adds a filter to be used on incoming messages. The data should include the player ID, licensee ID
     * and topic to filter. The socket should be a socket client instance where the filtered data should
     * be sent to. The filter will be removed if the 'disconnect' event is received.
     * @param socketData This data will be sent by the widgets.
     */
    addFilter(socketData: ISocketData) {
        /**
         * registrationKeys contains the filters per topic, as sent by the widget (e.g. ["playerID", "lid"]
         */
        let registrationKeys: Array<string> = [];
        each(KSSFilterCollection.getFilteredKeys(this._topic.topicName), (filterMap: IFilterMapping) => {
            registrationKeys.push(filterMap.registrationKey);
        });

        if (!this.isValidRegistration(socketData, registrationKeys)) {
            return;
        }

        let result: Array<Array<string>> = KSSFilterCollection.filters[this._topic.topicName].keyParser.getKeyPaths(
            socketData.data, registrationKeys);

        /**
         * The results will be used as path of keys pointing to the socket instance, allowing a fast check and
         * retrieval of a potential socket awaiting any kafka records for those values:
         *
         * filters: {
         *     740: {
         *         939: <socket>,
         *         940: <socket>>
         *     },
         *     ...
         * }
         */
        if (result && result.length > 0) {
            each(result, (customPath: Array<string>) => {
                let socketList: Array<SocketIOClient.Socket> = get(this._filters, customPath);
                if (isNil(socketList)) {
                    socketList = [];
                }
                socketList.push(socketData.socket);
                setWith(this._filters, customPath, socketList, Object);
            });

            if (isNil(this._sockets[socketData.socket.id])) {
                this._sockets[socketData.socket.id] = [];
            }
            this._sockets[socketData.socket.id] = this._sockets[socketData.socket.id].concat(result);

            KSSLogger.instance.log(KSSLogger.level.INFO,
                "filter_added",
                {
                    socketID: socketData.socket.id,
                    topic: `${socketData.data["topic"]}`,
                    filteredKeys: KSSFilterCollection.getFilteredKeys(this._topic.topicName),
                    filteredValues: result
                }
            );
            socketData.socket.on('error', (error: Error) => {
                KSSLogger.instance.log(KSSLogger.level.ERROR, 'socket_error',
                    {
                        error: error.message
                    });
            });
            socketData.socket.on('disconnect', (reason: string) => {
                if (socketData) {
                    KSSLogger.instance.log(KSSLogger.level.INFO,
                        "filter_removed",
                        {
                            socketID: socketData.socket.id,
                            topic: `${socketData.data["topic"]}`,
                            filteredKeys: KSSFilterCollection.getFilteredKeys(this._topic.topicName),
                            filteredValues: this._sockets[socketData.socket.id],
                            reason: `${reason}`
                        }
                    );
                    each(this._sockets[socketData.socket.id], (customPath: Array<string>) => {
                        //Get all the filters that have this socket
                        let arrayOfFilters: Array<object> = get(this._filters, customPath);
                        remove(arrayOfFilters, {"id": socketData.socket.id});
                        if (arrayOfFilters && arrayOfFilters.length === 0) {
                            setWith(this._filters, customPath, undefined, Object);
                            unset(this._filters, customPath);
                            for (let i = customPath.length - 1; i >= 0; i--) {
                                let iterationPath: Array<string> = customPath.slice(0, i);
                                if (isEmpty(get(this._filters, iterationPath))) {
                                    unset(this._filters, iterationPath);
                                }
                            }
                        }
                    });
                    this._sockets[socketData.socket.id] = undefined;
                    delete this._sockets[socketData.socket.id];
                    socketData.socket = undefined;
                    socketData = undefined;
                    this.checkFilteringStatus();
                } else {
                    KSSLogger.instance.log(KSSLogger.level.INFO,
                        "filter_removed",
                        {
                            topic: this._topic.topicName,
                            reason: `${reason}`
                        }
                    );
                }

            });
        }

    }

    /**
     * Toggles filtering on if there are filters added, or stops it of the filter list is empty.
     */
    checkFilteringStatus() {
        if (!this._filtering && !isEmpty(this._filters)) {
            this._subscription = this.messageObserver.subscribe(this._handleMessageSubscription);
            this._filtering = true;
        } else if (this._filtering && isEmpty(this._filters)) {
            this._subscription.unsubscribe();
            this._filtering = false;
        }
    }

    /**
     * Handler for the incoming message observable subscription. This is where the data is received.
     * @param message The message received.
     */
    messageSubscriptionHandler(message: string): void {
        let data = JSON.parse(message);
        data["topic"] = this._topic.topicName;
        if (KSSFilterCollection.filters[this._topic.topicName]) {
            for (let i = 0; i < this._topic.kafkaName.length; i++) {
                let filterData = data[this._topic.kafkaName[i].name];
                if (isNil(filterData)) {
                    continue;
                }
                let socketList: Array<SocketIOClient.Socket> =
                    KSSFilterCollection.filters[this._topic.topicName].filter.filter(filterData);
                each(socketList, (socket: SocketIOClient.Socket) => {
                    socket.emit(this._topic.kafkaName[i].event, data);
                });
            }
        } else {
            // TODO Topic with no filter defined
        }

    }

    /**
     * Definition of the error handler for consumer failure.
     * @param err The error received.
     */
    consumerErrorHandler(err: Error): void {
        // TODO Implement error handling (needs specs)
        KSSLogger.instance.log({
            level: KSSLogger.level.ERROR,
            message: JSON.stringify(err)
        });
    }

    private connectConsumer(topic: string, callback: Function) {
        KSSManager.admin.connect().then(() => {
            let earliest: boolean = false;
            KSSManager.admin.fetchTopicMetadata({topics: [topic]})
                .then((result: any) => {
                    KSSManager.admin.resetOffsets({groupId: this._groupId, topic, earliest})
                        .then(() => {
                            KSSManager.admin.disconnect().then(() => {
                                this.attach()
                                    .then(() => {
                                        KSSLogger.instance.log(KSSLogger.level.INFO,
                                            'consumer_connected',
                                            {
                                                topic: `${topic}`
                                            }
                                        );
                                        this._completedInit = true;
                                        callback();
                                    })
                                    .catch((e: Error) => {
                                        console.error(`${this._topic.topicName}: ${e.message}`, e);
                                        KSSLogger.instance.log(KSSLogger.level.ERROR,
                                            'consumer_error',
                                            {
                                                topic: `${this._topic.topicName}`,
                                                error: `${e.message}`
                                            }
                                        );
                                        this._completedInit = true;
                                        callback();
                                    });
                            });
                        })
                        .catch((e: Error) => {
                            KSSLogger.instance.log(KSSLogger.level.ERROR,
                                'consumer_error',
                                {
                                    topic: `${this._topic.topicName}`,
                                    error: `${e}`
                                }
                            );
                            // @ts-ignore
                            if (e.hasOwnProperty("retriable") && e["retriable"] === false) {
                                setTimeout(() => {
                                    this.connectConsumer(topic, callback);
                                }, 5000)
                            }

                        })
                })
                .catch(reason => {
                    KSSLogger.instance.log(KSSLogger.level.ERROR,
                        'consumer_error',
                        {
                            topic: `${this._topic.topicName}`,
                            error: `${reason}`
                        }
                    );
                    if (reason.type === 'UNKNOWN_TOPIC_OR_PARTITION') {
                        this._completedInit = true;
                        callback();
                    }
                })


        })
    }

    private initializeConsumer() {
        this._consumer = KSSManager.client.consumer({groupId: this._groupId});
    }

    /**
     * Adds the consumer events. Messaging is rerouted through the observable, while 'error' and
     * 'offsetOutOfRange' have their own handlers.
     */
    private addConsumerEvents() {
        /**
         * Handler for the message event from Kafka.
         */
        this.messageObserver = new Observable<unknown>(obs => {
            this.messageSubject.subscribe(obs);
        });
    }

    private attach(): Promise<any> {
        return new Promise<any>(async (resolve, reject) => {
            let topic: string = this._topic.topicName;
            await this._consumer.connect();
            await this._consumer.subscribe({topic});
            await this._consumer.run({
                // eachBatch: async ({ batch }) => {
                //   console.log(batch)
                // },
                eachMessage: async ({topic, partition, message}) => {
                    this.messageSubject.next(message.value.toString());
                },
            });
            resolve();
        })
    }

    private logSocketCount(): void {
        let socketCount: Array<Dictionary<any>> = [];
        let filteredKeys: IFilterMapping[] = KSSFilterCollection.getFilteredKeys(this._topic.topicName);
        let keyIndex = findIndex(filteredKeys,
            {"dataKeyName": KSSFilterCollection.socketCountBy[this._topic.topicName]});
        each(this._sockets, (data: Array<Array<string>>) => {
            each(data, (filterValues: Array<string>) => {
                let findFilter: Dictionary<string> = {};
                findFilter[filteredKeys[0].dataKeyName] = filterValues[0];
                let entry: Dictionary<any> = find(socketCount, findFilter);
                if (entry) {
                    if (keyIndex == 1) {
                        let countObject: { id: string, count: number } = find(entry[filteredKeys[1].dataKeyName], {'id': filterValues[1]});
                        if (countObject) {
                            countObject.count++;
                            return false;
                        } else {
                            entry[filteredKeys[1].dataKeyName].push({
                                id: filterValues[1],
                                count: 1
                            });
                            return false;
                        }
                    } else {
                        entry.count++;
                    }
                } else {
                    let newEntry: Dictionary<any> = {};
                    if (keyIndex == 1) {
                        newEntry[filteredKeys[0].dataKeyName] = filterValues[0];
                        newEntry[filteredKeys[1].dataKeyName] = [{
                            id: filterValues[1],
                            count: 1
                        }];
                        socketCount.push(newEntry);
                        return false;
                    } else {
                        let newEntry: Dictionary<any> = {};
                        newEntry[filteredKeys[0].dataKeyName] = filterValues[0];
                        newEntry['count'] = 1;
                        socketCount.push(newEntry);
                        return false;
                    }
                }
            })
        });
        each(socketCount, entry => {
            let logEntries: Array<Dictionary<any>> = [];
            if (keyIndex === 1) {
                each(entry[filteredKeys[1].dataKeyName], item => {
                    let logEntry: Dictionary<any> = {
                        action: "socket_count_report",
                        topic: `${this._topic.topicName}`
                    };
                    logEntry[filteredKeys[0].dataKeyName] = entry[filteredKeys[0].dataKeyName];
                    logEntry[filteredKeys[1].dataKeyName] = item["id"];
                    logEntry["count"] = item["count"];
                    logEntries.push(logEntry);
                })
                each(logEntries, logEntry => {
                    KSSLogger.instance.log(KSSLogger.level.INFO,
                        "socket_count_report",
                        logEntry
                    );
                })
            } else {
                let logEntry: Dictionary<any> = {
                    action: "socket_count_report",
                    topic: `${this._topic.topicName}`
                };
                logEntry[filteredKeys[0].dataKeyName] = entry[filteredKeys[0].dataKeyName];
                logEntry["count"] = entry["count"];
                KSSLogger.instance.log(KSSLogger.level.INFO,
                    "socket_count_report",
                    logEntry
                );
            }
        })
    }

    private startLogSocketCount(): void {
        setInterval(() => {
                this.logSocketCount();
            },
            60000)
    }

    private isValidRegistration(socketData: ISocketData, registrationKeys: Array<string>): boolean {
        let valid: boolean = true;
        each(registrationKeys, (key) => {
            if (key !== '') {
                if (isNil(socketData.data[key])) {
                    valid = false;
                    return false;
                }
            }
        })
        return valid;
    }
}

const f = <T>(a: T[], b: T[]) => [].concat(...a.map(d => b.map(e => [].concat(d, e))));
// @ts-ignore
export const cartesianProduct = <T>(a: T[], b: T[], ...c: T[][]) => {
    if (!b || b.length === 0)
        return a;
    const fab = f(a, b);
    const [b2, ...c2] = c;
    return cartesianProduct(fab, b2, c2);
};
