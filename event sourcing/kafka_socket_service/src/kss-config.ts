import config = require("config");
import {KSSLogger} from "./kss-logger";
import {ErrorConstants} from "./constants/error-constants";
import {IConfigSource} from "config";

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
 * Wrapper for the configuration package (https://www.npmjs.com/package/config), with the purpose of managing
 * configuration retrieval from the config json file. The config file is _config/default.json_
 */
export class Configuration {
    /**
     * Retrieves a key from the configuration file. If the key is missing, the app will crash with an error logged.
     * @param key The key to retrieve.
     */
    static get(key: string): any {
        const keyPath: string = `${key}`;
        if (config.has(keyPath)) {
            return config.get(keyPath);
        } else {
            KSSLogger.instance.log({
                level: KSSLogger.level.ERROR,
                message: ErrorConstants.MISSING_CONFIGURATION_PROPERTY
            });
            throw new Error(`Missing requested configuration property "${process.env.ENV}.${key}"`);
        }
    }

    static getConfigPaths(): IConfigSource[] {
        return config.util.getConfigSources();
    }
}
