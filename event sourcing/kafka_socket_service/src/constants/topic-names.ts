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

import {ITopicName} from "../interfaces/i-topic-name";

/**
 * Constants for topic names.
 */
export class TopicNames {
    /**
     * Topic used to send configuration updates to the app.
     */
    static CONFIGURATION: ITopicName = {
        topicName: 'configuration_update',
        kafkaName: [{name: 'configuration_update', event: 'feed'}]
    };
    /**
     * The topic for wallet transactions in GAP.
     */
    static WALLET: ITopicName = {
        topicName: 'wallet_transaction',
        kafkaName: [{name: 'wallet_transaction', event: 'feed'}]
    };
    /**
     * The topic for session start events.
     */
    static SESSION_START: ITopicName = {
        topicName: 'session_start_event',
        kafkaName: [{name: 'session_start_event', event: 'feed'}]
    };

    /**
     * Topic for game client details.
     */
    static GAME_DETAILS: ITopicName = {
        topicName: 'game_details',
        kafkaName: [{name: 'game_details', event: 'feed'}]
    };

    /**
     * Topic for Jackpot Engine transactions.
     */
    static JP_ENGINE_TRANSACTION: ITopicName = {
        topicName: 'jpengine_transaction',
        kafkaName: [{name: 'jackpot_engine_transaction', event: 'feed'}]
    };

    /**
     * Topic for realtime jackpot back office.
     */
    static JACKPOT_BO_REALTIME: ITopicName = {
        topicName: 'jackpot_backoffice_realtime',
        kafkaName: [
            { name: 'jackpot_backoffice_realtime', event: 'feed'},
            { name: 'jackpot_backoffice_realtime_win', event: 'win'}
        ],
    };

    /**
     * Topic for universal GAP message.
     */
    static UNIVERSAL_GAP_MESSAGE: ITopicName = {
        topicName: 'gap_generic_wallet_message',
        kafkaName: [{name: 'gap_generic_wallet_message', event: 'feed'}]
    };

    /**
     * Topic for universal GAP response.
     */
    static UNIVERSAL_GAP_RESPONSE: ITopicName = {
        topicName: 'widget_wallet_message_confirmation',
        kafkaName: [{name: 'widget_wallet_message_confirmation', event: 'feed'}]
    };

    /**
     * Topic for Competition Labs PoC live data widget
     */
    static COMPETITION_LABS_POC: ITopicName = {
        topicName: 'competition_labs_poc_freeround',
        kafkaName: [{name: 'competition_labs_poc_freeround', event: 'feed'}]
    };
}
