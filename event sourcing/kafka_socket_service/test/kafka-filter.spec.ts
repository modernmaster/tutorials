import {expect} from "chai";
import {KafkaFilter} from "../src/kafka-filter";

describe('Kafka Filter', () => {
    it('Should do nothing for filtering, this need to be overridden', done => {
        let filter: KafkaFilter = new KafkaFilter({}, []);
        let filteringResult = filter.filter({});
        expect(filteringResult).to.be.undefined;
        done();
    })
});
