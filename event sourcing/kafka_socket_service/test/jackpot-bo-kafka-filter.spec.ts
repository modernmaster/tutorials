import {expect} from "chai";
import {KSSSocketServer} from "../src/kss-socket-server";
import {KSSFilterCollection} from "../src/kss-filter-collection";
import {TopicNames} from "../src/constants/topic-names";
import {JackpotBOKafkaFilter} from "../src/topic_filters/jackpot-bo-kafka-filter";
import {each} from "lodash";
import {IFilterMapping} from "../src/interfaces/i-filter-mapping";

describe('Jackpot Backoffice Kafka Filter', () => {
    it('Should filter properly', done => {
        KSSSocketServer.init();
        KSSFilterCollection.registerFilters();
        const filters = {};
        KSSFilterCollection.initializeFilter("jackpot_backoffice_realtime", filters);
        const socket = require('socket.io-client')('http://localhost:3012');
        socket.on('connect', () => {
            const registrationKeys: Array<string> = [];
            each(KSSFilterCollection.getFilteredKeys("jackpot_backoffice_realtime"), (filterMap: IFilterMapping) => {
                registrationKeys.push(filterMap.registrationKey);
            });
            const socketData = {
                engineId: [5, 6]
            };
            const result: Array<Array<string>> = KSSFilterCollection.filters["jackpot_backoffice_realtime"].keyParser.getKeyPaths(
                socketData, registrationKeys);
            const filter: JackpotBOKafkaFilter = new JackpotBOKafkaFilter(
                {"5": [socket]},
                KSSFilterCollection.getFilteredKeys(TopicNames.JACKPOT_BO_REALTIME.topicName)
            );
            const [jpBORealtimeKafka] = TopicNames.JACKPOT_BO_REALTIME.kafkaName;
            const [filteringResult] = filter.filter(
                JSON.parse("{\"jackpot_backoffice_realtime\":{\"engineId\":5,\"progressiveId\":989,\"levelId\":1236,\"licenseeId\":628,\"operatorId\":0,\"currentSeed\":\"722.4042000000\",\"currentPrize\":\"0.0812000000\",\"currency\":\"EUR\",\"closeToWin\":false, \"progressiveInitialParentId\":989, \"levelInitialParentId\":1236,\"time\":1584353085,\"environment\":\"bahamas.isoftbet.com\",\"environment_type\":\"qa\"}}")[jpBORealtimeKafka.name]
            );
            expect(filteringResult).to.not.be.undefined;
            expect(filteringResult).to.equal(socket);
            KSSSocketServer.destroy();
            done();
        });
    });
});
