import * as config from 'config';
import {KSSLogger} from "../src/kss-logger";
import {expect, use} from "chai";
import spies = require("chai-spies");

use(spies);

describe('Start up', () => {
    it('Should initialise the logger', (done => {
        expect(KSSLogger.instance).to.not.be.undefined;
        done();
    }));
    it('Should have a valid configuration', (done) => {
        expect(process.env.ENV).to.be.a('string');
        expect(config.get("kafkaBrokers")).to.be.an("array");
        expect(config.get("kafkaGroup")).to.be.a("string");
        expect(config.get("socketPort")).to.be.a("number");
        expect(config.get("socketUsesHTTPS")).to.be.a("boolean");
        done();
    });
    it('Should log on missing ENV', (done) => {
        done();
    });
});
