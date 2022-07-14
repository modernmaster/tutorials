import * as path from "path";
import {Configuration} from "../src/kss-config";
import {expect, spy} from "chai";
import {KSSLogger} from "../src/kss-logger";

describe('Config', () => {
    it('Should log and error when an non-existent key is requested', (done) => {
        let logSpy = spy.on(KSSLogger.instance, 'log');
        expect(() => {
            Configuration.get('non-existent-key')
        }).to.throw();
        expect(logSpy).to.have.been.called();
        spy.restore(KSSLogger.instance, 'log');
        done();
    });

    it('Should properly read the path to config', done => {
        let configPaths = Configuration.getConfigPaths();
        expect(configPaths).to.be.an('array');
        let pathFound = false;
        const expectedConfigPath = path.join(path.normalize(process.env.NODE_CONFIG_DIR), 'default.json');
        for (let i = 0; i < configPaths.length; i++) {
            const configPath = path.normalize(configPaths[i]["name"]);
            if (configPath === expectedConfigPath) {
                pathFound = true;
            }
        }
        expect(pathFound).to.be.true;
        done();
    })
});
