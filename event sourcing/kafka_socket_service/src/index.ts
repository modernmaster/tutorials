import {KSSLogger} from "./kss-logger";
import {isNil} from "lodash";
import {KSSManager} from "./kss-manager";
import {KSSSocketServer} from "./kss-socket-server";
import {TopicNames} from "./constants/topic-names";
import {KSSFilterCollection} from "./kss-filter-collection";
import * as Sentry from '@sentry/node';
import {Configuration} from "./kss-config";
import {KSSProducerAPI} from "./kss-producer-api";
import Signals = NodeJS.Signals;


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

Sentry.init({
    dsn: Configuration.get('sentryDSN'),
    environment: process.env.ENV
});

/**
 * Startup class for the app. This class will check the environment, add the configuration consumer,
 * start the socket server and add the hook for the widget registration.
 */
export class KSS {
    /**
     * Runs the initialisation procedure.
     */
    static init() {
        KSS._checkProperEnvironment();
        KSS.addErrorHandling();
        KSSManager.init();
        KSSManager.injectConfigurationConsumer();
        KSSLogger.instance.log({
            level: KSSLogger.level.INFO,
            message: 'App constructed.'
        });
    }

    /**
     * Starts the socket server.
     */
    static startSocketServer() {
        KSSSocketServer.init();
    }

    /**
     * Stops the socket server.
     */
    static stopSocketServer() {
        KSSSocketServer.destroy();
    }

    private static _checkProperEnvironment() {
        if (isNil(process.env.ENV)) {
            process.env.ENV = 'dev';
        }
        KSSLogger.instance.log({
            level: KSSLogger.level.INFO,
            message: `Environment set to ${process.env.ENV}`
        })
    }

    private static addErrorHandling() {
        const errorTypes = ['unhandledRejection', 'uncaughtException'];
        const signalTraps = ['SIGTERM', 'SIGINT', 'SIGUSR2'];
        errorTypes.map((type: Signals) => {
            process.on(type, e => {
                try {
                    Sentry.captureException(e);
                    console.log(`process.on ${type}`);
                    console.error(e);
                    KSSLogger.instance.log(KSSLogger.level.ERROR,
                        JSON.stringify({
                                // topic: `${this._topic.topicName}`,
                                error: `${e}`,
                                type: `${type}`
                            }
                        ));
                    KSSManager.destroy().then(() => {
                        process.exit(0)
                    });
                } catch (_) {
                    process.exit(1)
                }
            })
        });

        signalTraps.map((type: Signals) => {
            process.once(type, async () => {
                try {
                    KSSLogger.instance.log(KSSLogger.level.INFO,
                        JSON.stringify({
                                // topic: `${this._topic.topicName}`,
                                signal: `${type}`
                            }
                        ));
                    await KSSManager.destroy();
                } finally {
                    process.kill(process.pid, type)
                }
            })
        });
    }
}

KSS.init();
KSS.startSocketServer();
const producerAPI = new KSSProducerAPI();
KSSSocketServer.getObservableForEvent('register').subscribe((socketData) => {
    KSSManager.registerWidget(socketData);
});
KSSSocketServer.getObservableForEvent('reply').subscribe((socketData) => {
    // KSSLogger.instance.log(KSSLogger.level.INFO, `Reply from socket ${socketData.socket.id}: ${JSON.stringify(socketData.data)}`);
    for (let i = 0; i < TopicNames.UNIVERSAL_GAP_RESPONSE.kafkaName.length; i++) {
        if (socketData.data['topic'] === TopicNames.UNIVERSAL_GAP_RESPONSE.kafkaName[i].name) {
            producerAPI.sendToKafka(socketData.data);
        }
    }
});
KSSFilterCollection.registerFilters();
KSSManager.loadConfiguration({
    consumers: [
        TopicNames.WALLET,
        TopicNames.JP_ENGINE_TRANSACTION,
        TopicNames.JACKPOT_BO_REALTIME,
        TopicNames.UNIVERSAL_GAP_MESSAGE,
        TopicNames.UNIVERSAL_GAP_RESPONSE,
        TopicNames.COMPETITION_LABS_POC
    ]
});

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.WALLET.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.JP_ENGINE_TRANSACTION.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.JACKPOT_BO_REALTIME.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.UNIVERSAL_GAP_MESSAGE.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.UNIVERSAL_GAP_RESPONSE.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}

if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
    KSSManager.getConsumer(TopicNames.COMPETITION_LABS_POC.topicName).messageObserver.subscribe((message: string) => {
        KSSLogger.instance.log(KSSLogger.level.INFO, message);
    });
}
