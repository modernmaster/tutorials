import {createLogger, format, Logger, transports} from "winston";
import {isNil} from "lodash";
import {LogEntry, logLevel} from "kafkajs";
import {Configuration} from "./kss-config";
import {TransformableInfo} from "logform";

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
 * Singleton wrapper for the _winston_ node logger module. (https://www.npmjs.com/package/winston)
 */
export class KSSLogger {
    /**
     * Defines levels of logging.
     */
    static level = {
        INFO: 'info',
        ERROR: 'error'
    };
    private static _instance: Logger;
    /**
     * Returns the singleton instance.
     */
    static get instance(): Logger {
        if (isNil(KSSLogger._instance)) {
            let fileName: string;
            KSSLogger._instance = createLogger({
                level: KSSLogger.level.INFO,
                format: format.combine(
                    format((info: TransformableInfo) => {
                        info["kss_environment"] = Configuration.get('kafkaGroup');
                        return info;
                    })(),
                    format.timestamp(),
                    format.json(),
                ),
                transports: [
                    //
                    // - Write to all logs with level `info` and below to `combined.log`
                    // - Write all logs error (and below) to `error.log`.
                    //
                    // new transports.File({filename: 'error.log', level: KSSLogger.level.ERROR}),
                    process.env.ENV === 'dev' || process.env.HOST === 'local'
                        ? new transports.File({filename: 'kss-combined.json'})
                        : new transports.File({dirname: '/var/log/apps', filename: 'kss-combined.json'})
                ]
            });
            if (process.env.ENV === 'dev' || process.env.HOST === 'local') {
                KSSLogger._instance.add(new transports.Console({
                    format: format.simple()
                }));
            }
        }
        return KSSLogger._instance;
    }

    static logForward(logLevel: string): (entry: LogEntry) => void {
        return ({ namespace, level, label, log }) => {
            const { message, ...extra } = log;
            KSSLogger._instance.log({
                level: toWinstonLogLevel(level),
                message,
                extra,
            })
        }
    }
}

const toWinstonLogLevel = (level: number) => {
    switch(level) {
        case logLevel.ERROR:
        case logLevel.NOTHING:
            return 'error';
        case logLevel.WARN:
            return 'warn';
        case logLevel.INFO:
            return 'info';
        case logLevel.DEBUG:
            return 'debug'
    }
}
