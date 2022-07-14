import {createServer, IncomingMessage, Server, ServerResponse} from "http";
import {readFile, readFileSync} from "fs";
import {Configuration} from "./kss-config";
import {KSSLogger} from "./kss-logger";
import {Observable} from "rxjs";
import * as https from "https";
import {dirname, join} from "path";
import {IConfigSource} from "config";
import {ErrorConstants} from "./constants/error-constants";
import SocketIO = require("socket.io");

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

/**
 * Wrapper for the socket.io module (https://socket.io/)
 */
export class KSSSocketServer {
    private static _app: Server | https.Server;
    private static _io: SocketIO.Server;

    /**
     * Initialises the server.
     */
    static init() {
        if (Configuration.get("socketUsesHTTPS") && process.env.HOST !== 'local') {
            let paths: IConfigSource[] = Configuration.getConfigPaths();
            let path: string;
            if (paths && paths.length === 1) {
                path = dirname(paths[0].name);
            } else {
                KSSLogger.instance.log({
                    level: KSSLogger.level.ERROR,
                    message: ErrorConstants.MULTIPLE_CONFIG_FILES
                });
                throw new Error(ErrorConstants.MULTIPLE_CONFIG_FILES);
            }
            const options: https.ServerOptions = {
                key: readFileSync(join(path, Configuration.get("pathToKey") as string)),
                cert: readFileSync(join(path, Configuration.get("pathToCert") as string)),
                minVersion: "TLSv1.2"
            };
            KSSSocketServer._app = https.createServer(options, KSSSocketServer._handler);
        } else {
            KSSSocketServer._app = createServer(KSSSocketServer._handler);
        }
        KSSSocketServer._io = SocketIO(KSSSocketServer._app, {
            pingTimeout: 15000
        });
        KSSSocketServer._app.listen(Configuration.get('socketPort'));
        KSSLogger.instance.log(KSSLogger.level.INFO, `Socket server started, listening on port ${Configuration.get('socketPort')}`);
    }

    /**
     * Creates an observable from the 'connection' even handler of socket.io. This will forward any message from a
     * client to the subscribers.
     * @param event The name of the socket event to monitor.
     */
    static getObservableForEvent(event: string): Observable<{ data: any, socket: any }> {
        return new Observable(observer => {
            KSSSocketServer._io.on('connection', (socket) => {
                socket.on(event, (data) => {
                    observer.next({data: data, socket: socket});
                });
            })
        });
    }

    /**
     * Destroys the socket.io server.
     */
    static destroy() {
        if (KSSSocketServer._io) {
            for (let socketsKey in KSSSocketServer._io.sockets.sockets) {
                KSSSocketServer._io.sockets.sockets[socketsKey].disconnect(true);
            }
            KSSSocketServer._io.close();
            KSSSocketServer._io = undefined;
            KSSSocketServer._app.close();
            KSSSocketServer._app = undefined;
            KSSLogger.instance.log(KSSLogger.level.INFO, `Socket server destroyed.`);
        }
    }

    private static _handler(req: IncomingMessage, res: ServerResponse) {
        if (req.url === '/version') {
            readFile('VERSION',
                (err, data) => {
                    if (err) {
                        res.writeHead(500);
                        return res.end('Error loading VERSION');
                    }

                    res.writeHead(200);
                    return res.end(data.toString());
                });
        } else {
            readFile(__dirname + '/index.html',
                (err, data) => {
                    if (err) {
                        res.writeHead(500);
                        return res.end('Error loading index.html');
                    }

                    res.writeHead(200);
                    res.end(data);
                });
        }
    }

    public static getSocketCount(): number {
        return Object.keys(KSSSocketServer._io.sockets.sockets).length;
    }
}
