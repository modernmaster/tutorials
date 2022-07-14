import {Dictionary, isNil} from "lodash";
import {TopicNames} from "./constants/topic-names";
import {JackpotBOKeys, JPEngineKeys, UGAPMKeys, WalletKeys} from "./interfaces/data/i-kafka-keys";
import {IFilterMapping} from "./interfaces/i-filter-mapping";
import {KafkaFilter} from "./kafka-filter";
import {WalletKafkaFilter} from "./topic_filters/wallet-kafka-filter";
import {JPEngineKafkaFilter} from "./topic_filters/jpengine-kafka-filter";
import {JackpotBOKafkaFilter} from "./topic_filters/jackpot-bo-kafka-filter";
import {FilterKeysDefinition} from "./filter_keys_definitions/filter-keys-definition";
import {JPEngineFilterKeysDefinition} from "./filter_keys_definitions/jpengine-filter-keys-definition";
import {WalletFilterKeysDefinition} from "./filter_keys_definitions/wallet-filter-keys-definition";
import {JackpotBOFilterKeysDefinition} from "./filter_keys_definitions/jackpot-bo-filter-keys-definition";
import {UGAPMKafkaFilter} from "./topic_filters/ugapm-kafka-filter";
import {CLPOCKafkaFilter} from "./topic_filters/clpoc-kafka-filter";

/**
 *  iSOFTBET
 *  Copyright 2019 iSOFTBET
 *  All Rights Reserved.
 *
 *  NOTICE: You may not use, distribute or modify this document without the
 *  written permission of its copyright owner
 *
 *  Created by George Carată on 10/02/20.
 */

/**
 * Class storing the filtered keys and classes per topic
 */
export class KSSFilterCollection {
    static get socketCountBy(): Dictionary<string> {
        return this._socketCountBy;
    }
    static get filters(): Dictionary<{filter: KafkaFilter, keyParser: FilterKeysDefinition}> {
        return this._filters;
    }

    static getFilteredKeys(topic: string): Array<IFilterMapping> {
        return this._filteredKeys[topic];
    }

    /**
     * Dictionary storing the keys that contain values which need to be checked in order to filter Kafka records, per
     * topic.
     */
    private static _filteredKeys: Dictionary<Array<IFilterMapping>> = {};
    /**
     * Dictionary of instantiated filters, per topic
     */
    private static _filters: Dictionary<{filter: KafkaFilter, keyParser: FilterKeysDefinition}> = {};
    /**
     * Dictionary of available filters, per topic
     */
    private static _availableFilters: Dictionary<{def: Function, keyDefinition: Function}> = {};

    private static _socketCountBy: Dictionary<string> = {};

    /**
     *
     */
    private static filterForWallet(): void {
        KSSFilterCollection._filteredKeys[TopicNames.WALLET.topicName] = [
            {
                dataKeyName: WalletKeys.LICENSEE_ID,
                registrationKey: "lid"
            },
            {
                dataKeyName: WalletKeys.PLAYER_ID,
                registrationKey: "playerID"
            },
            {
                dataKeyName: WalletKeys.SKIN_ID,
                registrationKey: "skinID"
            }
        ];
        KSSFilterCollection._socketCountBy[TopicNames.WALLET.topicName] = WalletKeys.LICENSEE_ID;
    }

    private static filterForJPEngine(): void {
        KSSFilterCollection._filteredKeys[TopicNames.JP_ENGINE_TRANSACTION.topicName] = [
            {
                dataKeyName: JPEngineKeys.LICENSEE_ID,
                registrationKey: "lid"
            },
            {
                dataKeyName: JPEngineKeys.PLAYER_ID,
                registrationKey: "playerID"
            }
        ];
        KSSFilterCollection._socketCountBy[TopicNames.JP_ENGINE_TRANSACTION.topicName] = JPEngineKeys.LICENSEE_ID;
    }

    private static filterForJPBO(): void {
        KSSFilterCollection._filteredKeys[TopicNames.JACKPOT_BO_REALTIME.topicName] = [
            {
                dataKeyName: JackpotBOKeys.ENGINE_ID,
                registrationKey: "engineId"
            }
        ];
        KSSFilterCollection._socketCountBy[TopicNames.JACKPOT_BO_REALTIME.topicName] = JackpotBOKeys.ENGINE_ID;
    }

    /**
     *
     */
    private static filterForUGAPM(): void {
        KSSFilterCollection._filteredKeys[TopicNames.UNIVERSAL_GAP_MESSAGE.topicName] = [
            {
                dataKeyName: UGAPMKeys.OPERATOR_NAME,
                registrationKey: "operator"
            },
            {
                dataKeyName: UGAPMKeys.LICENSEE_ID,
                registrationKey: "lid"
            },
            {
                dataKeyName: UGAPMKeys.PLAYER_ID,
                registrationKey: "playerID"
            },
            {
                dataKeyName: UGAPMKeys.SKIN_ID,
                registrationKey: "skinID"
            }
        ];
        KSSFilterCollection._socketCountBy[TopicNames.UNIVERSAL_GAP_MESSAGE.topicName] = UGAPMKeys.OPERATOR_NAME;
    }

    static registerFilters() {
        KSSFilterCollection.filterForWallet();
        this._availableFilters[TopicNames.WALLET.topicName] = {
            def: WalletKafkaFilter,
            keyDefinition: WalletFilterKeysDefinition
        };
        KSSFilterCollection.filterForJPEngine();
        this._availableFilters[TopicNames.JP_ENGINE_TRANSACTION.topicName] = {
            def: JPEngineKafkaFilter,
            keyDefinition: JPEngineFilterKeysDefinition
        };
        KSSFilterCollection.filterForJPBO();
        this._availableFilters[TopicNames.JACKPOT_BO_REALTIME.topicName] = {
            def: JackpotBOKafkaFilter,
            keyDefinition: JackpotBOFilterKeysDefinition
        };
        KSSFilterCollection.filterForUGAPM();
        this._availableFilters[TopicNames.UNIVERSAL_GAP_MESSAGE.topicName] = {
            def: UGAPMKafkaFilter,
            keyDefinition: FilterKeysDefinition
        };
        KSSFilterCollection.filterForCLPOC();
        this._availableFilters[TopicNames.COMPETITION_LABS_POC.topicName] = {
            def: CLPOCKafkaFilter,
            keyDefinition: FilterKeysDefinition
        };
    }

    static initializeFilter(topicName: string, filters: Dictionary<any>) {
        if (!isNil(this._availableFilters[topicName])) {
            this._filters[topicName] = {
                filter: new (Function.prototype.bind.apply(this._availableFilters[topicName].def,
                    [null].concat([filters, this._filteredKeys[topicName]]))),
                keyParser: new (Function.prototype.bind.apply(this._availableFilters[topicName].keyDefinition,
                    [null].concat([])))
            }
        }
    }

    /**
     * Filter for Competition Labs PoC
     */
    private static filterForCLPOC(): void {
        KSSFilterCollection._filteredKeys[TopicNames.COMPETITION_LABS_POC.topicName] = [
            {
                dataKeyName: UGAPMKeys.OPERATOR_NAME,
                registrationKey: "operator"
            },
            {
                dataKeyName: UGAPMKeys.LICENSEE_ID,
                registrationKey: "lid"
            },
            {
                dataKeyName: UGAPMKeys.PLAYER_ID,
                registrationKey: "playerID"
            },
            {
                dataKeyName: UGAPMKeys.SKIN_ID,
                registrationKey: "skinID"
            }
        ];
        KSSFilterCollection._socketCountBy[TopicNames.COMPETITION_LABS_POC.topicName] = UGAPMKeys.OPERATOR_NAME;
    }
}
