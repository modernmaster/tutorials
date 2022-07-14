import {KSSManager} from "../src/kss-manager";
import {expect, spy} from "chai";
import {KSSLogger} from "../src/kss-logger";
import {TopicNames} from "../src/constants/topic-names";

describe('Manager', () => {
    it('Should destroy existing consumer on loading a config', (done => {
        KSSManager.destroy();
        KSSManager.loadConfiguration({
            consumers: [TopicNames.WALLET]
        });
        let destroySpy = spy.on(KSSManager.getConsumer(TopicNames.WALLET.topicName), 'destroy');
        expect(destroySpy).to.not.have.been.called();
        KSSManager.loadConfiguration({
            consumers: [TopicNames.WALLET]
        });
        expect(destroySpy).to.have.been.called();
        done();
    }));

    it("Should properly add consumers", (done) => {
        KSSManager.destroy();
        KSSManager.addConsumer({topicName: 'testTopic1', kafkaName: [{name: "", event: ""}]});
        let testInstance1 = KSSManager.getConsumer('testTopic1');
        KSSManager.addConsumer({topicName: 'testTopic1', kafkaName: [{name: "", event: ""}]}, false);
        let testInstance2 = KSSManager.getConsumer('testTopic1');
        expect(testInstance1).to.be.equal(testInstance2);
        KSSManager.destroy();
        done();
    });

    it("Should add filter for widget", (done) => {
        KSSManager.init();
        KSSManager.loadConfiguration({
            consumers: [TopicNames.WALLET]
        });
        let addFilterSpy = spy.on(KSSManager.getConsumer(TopicNames.WALLET.topicName), 'addFilter');
        let checkFilteringSpy = spy.on(KSSManager.getConsumer(TopicNames.WALLET.topicName), 'checkFilteringStatus');
        KSSManager.registerWidget({
            data: {
                topic: TopicNames.WALLET.topicName,
                lid: "123",
                playerID: "testUser"
            },
            socket: require('socket.io-client')('http://localhost:3012')
        });
        expect(addFilterSpy).to.have.been.called();
        expect(checkFilteringSpy).to.have.been.called();
        KSSManager.destroy();
        done();
    })
});
