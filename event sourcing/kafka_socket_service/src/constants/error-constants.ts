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
 * String constants used throughout the app.
 */
export class ErrorConstants {
    static MISSING_ENV: string = 'Missing "ENV" node variable. Please set it to the proper environment used (stage/prod)';
    static MISSING_CONFIGURATION_PROPERTY: string = "There is a missing configuration property. Please check the error message.";
    static CONFIG_UPDATE_INVALID_TYPE: string = "The configuration update sent an invalid type, expected 'consumer' or 'producer'";
    static MULTIPLE_CONFIG_FILES: string = "Multiple config files detected";
}
