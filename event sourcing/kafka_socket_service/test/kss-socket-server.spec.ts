import {assert, expect, spy} from "chai";
import {KSSSocketServer} from "../src/kss-socket-server";
import {KSSLogger} from "../src/kss-logger";
import {Observable} from "rxjs";
import {Configuration} from "../src/kss-config";
import {IncomingMessage} from "http";
import http = require("http");
import * as https from "https";
import * as fs from "fs";

describe('Socket server', () => {
    before(() => {
        KSSSocketServer.destroy();
    });
    beforeEach(() => {
        KSSSocketServer.init();
    });
    afterEach(() => {
        KSSSocketServer.destroy();
    });
    it('Should instantiate correctly', (done => {
        KSSSocketServer.destroy();
        let usesHTTPS = Configuration.get('socketUsesHTTPS');
        if (!usesHTTPS) {
            let serverCreationSpy = spy.on(http, 'createServer');
            KSSSocketServer.init();
            expect(serverCreationSpy).to.have.been.called();
            spy.restore(http, 'createServer');
            KSSSocketServer.destroy();
        } else {
            let serverCreationSpy = spy.on(https, 'createServer');
            KSSSocketServer.init();
            expect(serverCreationSpy).to.have.been.called();
            spy.restore(https, 'createServer');
            KSSSocketServer.destroy();
        }
        done();
    }));

    it("Should create observable for events", (done) => {
        let testObservable = KSSSocketServer.getObservableForEvent('test');
        assert.instanceOf(testObservable, Observable);
        done();
    });

    it("Should properly respond to http requests", (done) => {
        http.get(`http://localhost:${Configuration.get('socketPort')}`, (res: IncomingMessage) => {
            expect(res.statusCode).to.equal(500);
            done();
        }).on('error', (error) => {
            done();
        })
    });

    it('Should destroy correctly', (done => {
        let logSpy = spy.on(KSSLogger.instance, 'log');
        KSSSocketServer.destroy();
        expect(logSpy).to.have.been.called();
        spy.restore(KSSLogger.instance, 'log');
        done();
    }));
});

const writeConfig = (key: string, value: any) => {
    let filePath = process.env.NODE_CONFIG_DIR + '/default.json';
    let content = fs.readFileSync(filePath, { encoding: 'utf8'});
    console.log(content);
    let parsedContent = JSON.parse(content);
    parsedContent[key] = value;
    fs.writeFileSync(filePath, JSON.stringify(parsedContent), {encoding: 'utf8'});
};
