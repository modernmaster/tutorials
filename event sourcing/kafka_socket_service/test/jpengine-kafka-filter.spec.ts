import {expect} from "chai";
import {KafkaFilter} from "../src/kafka-filter";
import {KSSSocketServer} from "../src/kss-socket-server";
import {JPEngineKafkaFilter} from "../src/topic_filters/jpengine-kafka-filter";
import {KSSFilterCollection} from "../src/kss-filter-collection";
import {TopicNames} from "../src/constants/topic-names";

describe('Jackpot Engine Kafka Filter', () => {
    // xit('Should filter contributions', done => {
    //     KSSSocketServer.init();
    //     KSSFilterCollection.registerFilters();
    //     let socket = require('socket.io-client')('http://localhost:3012');
    //     socket.on('connect', () => {
    //         let filter: JPEngineKafkaFilter = new JPEngineKafkaFilter(
    //             {
    //                 628: {
    //                     "Ashden": [socket]
    //                 }
    //             },
    //             KSSFilterCollection.getFilteredKeys(TopicNames.JP_ENGINE_TRANSACTION.topicName));
    //         let filteringResult = filter.filter(
    //             JSON.parse("{\"jackpot_engine_transaction\":{\"currency\":\"EUR\",\"environment_domain\":\"qa.isoftbet.com\",\"environment_type\":\"qa\",\"jackpot_contributions\":{\"bet\":2000,\"contributions\":[{\"exchange_rate\":1,\"jackpot_currency\":\"EUR\",\"prize_contribution\":200,\"prize_contribution_player_currency\":200,\"progressive_id\":1069,\"progressive_level_id\":1322,\"seed_contribution\":0,\"seed_contribution_player_currency\":0,\"total_prize_contribution\":74388.752,\"total_prize_contribution_player_currency\":74388.752,\"total_seed_contribution\":1013.248,\"total_seed_contribution_player_currency\":1013.248}],\"transaction_date\":\"2020-03-16 07:28:44\",\"transaction_id\":\"1584343724683066994701877978159-njecontrib\"},\"licensee_id\":628,\"operator\":\"0\",\"player_id\":\"Ashden\",\"provider_id\":1,\"skin_id\":200249,\"timestamp\":\"2020-03-16 07:29:04+0000\",\"transaction_type\":\"JPE_CONTRIBUTION\"}}")[TopicNames.JP_ENGINE_TRANSACTION.kafkaName]
    //         );
    //         expect(filteringResult[0]).to.equal(socket);
    //         KSSSocketServer.destroy();
    //         done();
    //     });
    // });

    it('Should filter jackpot win', done => {
        KSSSocketServer.init();
        const socket = require('socket.io-client')('http://localhost:3012');
        const jpEngineTransaction = TopicNames.JP_ENGINE_TRANSACTION;
        socket.on('connect', () => {
            const filter: JPEngineKafkaFilter = new JPEngineKafkaFilter(
                {
                    628: {
                        "Ashden": [socket]
                    }
                },
                KSSFilterCollection.getFilteredKeys(jpEngineTransaction.topicName)
            );
            const [jpEngineTransactionKafka] = jpEngineTransaction.kafkaName;
            const [filteringResult] = filter.filter(
                JSON.parse("{\"jackpot_engine_transaction\":{\"bet_amount\":5000,\"currency\":\"EUR\",\"environment_domain\":\"qa.isoftbet.com\",\"environment_type\":\"qa\",\"jackpot_wins\":{\"amount_jackpot_currency\":682,\"amount_player_currency\":682,\"exchange_rate\":1,\"jackpot_currency\":\"EUR\",\"progressive_id\":1436,\"progressive_level_id\":2197,\"transaction_date\":\"2020-04-15 13:01:15\",\"transaction_id\":\"1586955674720577081509750798018-njewin\",\"wallet_approved\":false},\"licensee_id\":628,\"operator\":\"0\",\"player_id\":\"Ashden\",\"provider_id\":1,\"skin_id\":200249,\"timestamp\":\"2020-04-15 13:01:42+0000\",\"transaction_type\":\"JPE_WIN\",\"trigger_type\":0},\"topic\":\"jpengine_transaction\"}")[jpEngineTransactionKafka.name]
            );
            expect(filteringResult).to.equal(socket);
            KSSSocketServer.destroy();
            done();
        });

    })
});
