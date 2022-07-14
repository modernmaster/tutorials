import {KSSConsumer} from "../src/kss-consumer";
import {describe} from 'mocha';
import {expect, spy} from "chai";
import {KSSLogger} from "../src/kss-logger";
import {TopicNames} from "../src/constants/topic-names";
import {KSSManager} from "../src/kss-manager";
import {KSSFilterCollection} from "../src/kss-filter-collection";
import {KSSSocketServer} from "../src/kss-socket-server";

describe('Consumer', () => {
    it('Should instantiate correctly', (done) => {
        let instance = new KSSConsumer(TopicNames.WALLET);
        expect(instance).to.not.be.undefined;
        instance.destroy();
        done();
    });

    it("Should add filters", (done) => {
        let instance = new KSSConsumer(TopicNames.WALLET);
        KSSSocketServer.init();
        KSSFilterCollection.registerFilters();
        let socket = require('socket.io-client')('http://localhost:3012');
        socket.on('connect', () => {
            instance.addFilter({
                data: {
                    lid: "123",
                    playerID: "testUser",
                    skinID: "200241",
                    topic: "test"
                },
                socket: socket
            });
            let filter = instance.filters['123']['testUser'];
            expect(filter).not.to.be.undefined;
            // expect(filter.name).to.equal("testSocket");
            let logSpy = spy.on(KSSLogger.instance, 'log');
            socket.listeners('error').forEach((value: any) => {
                value("testError")
            });
            expect(logSpy).to.have.been.called();
            spy.restore(KSSLogger.instance, 'log');
            socket.listeners('disconnect').forEach((value: any) => {
                value("disconnect")
            });
            expect(instance.filters).to.be.empty;
            instance.destroy();
            done();
        });

    });

    it("Should return the consumer instance", (done) => {
        let instance = new KSSConsumer(TopicNames.WALLET);
        expect(instance).to.not.be.undefined;
        instance.destroy();
        done();
    });

    it("Should actively filter when filters exist", (done) => {
        KSSManager.init();
        let instance = new KSSConsumer(TopicNames.WALLET);
        let socket = require('socket.io-client')('http://localhost:3012');
        expect(instance.filtering).to.be.false;
        socket.on('connect', () => {
            instance.addFilter({
                data: {
                    lid: "123",
                    playerID: "testUser",
                    skinID: "200241",
                    topic: "test"
                },
                socket: socket
            });
            let messagingSpy = spy.on(instance.messageObserver, 'subscribe');
            instance.checkFilteringStatus();
            expect(messagingSpy).to.have.been.called();
            spy.restore(instance.messageObserver, 'subscribe');
            expect(instance.filtering).to.be.true;
            socket.listeners('disconnect').forEach((value: any) => {
                value("disconnect")
            });
            expect(instance.filtering).to.be.false;
            instance.destroy();
            KSSManager.destroy();
            done();
        });

    });

    it('Should forward filtered messages', (done) => {
        KSSManager.init();
        let instance = new KSSConsumer(TopicNames.WALLET);
        let socket = require('socket.io-client')('http://localhost:3012');
        socket.on('connect', () => {
            instance.addFilter({
                data: {
                    lid: "123",
                    playerID: "testUser",
                    skinID: "200241",
                    topic: "wallet_transaction"
                },
                socket: socket
            });
            instance.checkFilteringStatus();
            let emitSpy = spy.on(instance.filters['123']['testUser']["200241"][0], 'emit');
            instance.messageSubscriptionHandler("{\"wallet_transaction\":{\"environment_type\":\"qa\",\"environment_domain\":\"qa.isoftbet.com\",\"provider_id\":1,\"licensee_id\":123,\"operator\":\"0\",\"player_id\":\"testUser\",\"currency\":\"EUR\",\"country\":\"GB\",\"skin_id\":200241,\"session_id\":\"15698426379aq7oGemFtEd3KTIb5aQrn\",\"transaction_type\":\"WIN\",\"amount\":37100000,\"coin_value\":1000,\"lines\":1,\"line_bet\":10,\"free_spin\":2,\"round_id\":\"15695136595780763465496745311739\",\"transaction_id\":\"156984269630015774220064415155\",\"first_round_transaction\":0,\"last_round_transaction\":0,\"transaction_date\":\"2019-09-30 11:24:56\",\"player_balance\":2785144115,\"JPW\":0,\"JPW_from_JPC\":0,\"fr_id\":null,\"fr_version\":null,\"fr_bonus\":null,\"fr_bonus_id\":null,\"last_fr\":null,\"JPC\":null,\"JP_value\":null,\"custom_parameter\":null,\"custom_values\":null,\"custom_parameters\":null},\"topic\":\"wallet_transaction\"}"
            );
            expect(emitSpy).to.have.been.called();
            spy.restore(instance.filters['123']['testUser']["200241"][0], 'emit');
            instance.destroy();
            KSSManager.destroy();
            done();
        });
    });

    it("Should log errors", (done) => {
        let instance = new KSSConsumer(TopicNames.WALLET);
        let logSpy = spy.on(KSSLogger.instance, 'log');
        instance.consumerErrorHandler(new Error("Test Error"));
        expect(logSpy).to.have.been.called();
        spy.restore(KSSLogger.instance, 'log');
        instance.destroy();
        done();
    });
});
