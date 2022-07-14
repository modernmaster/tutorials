import * as https from "https";
import {Configuration} from "./kss-config";
import * as querystring from "querystring";
import {KSSLogger} from "./kss-logger";
import {IResponseToken} from "./interfaces/i-response-token";

export class KSSProducerAPI {
    token: string

    constructor() {
        this.getAndSaveToken();
    }

    private getAndSaveToken() {
        this.getToken().then((token: string) => {
            this.token = token;
        }).catch(e => {
            KSSLogger.instance.log({
                level: KSSLogger.level.ERROR,
                message: JSON.stringify(e)
            });
        });
    }

    public sendToKafka(payload: any) {
        if (this.token) {
            this.makeRequest({
                host: Configuration.get("kafkaProducerAPIHost"),
                path: `${Configuration.get("kafkaProducerAPIPath")}?client_id=${Configuration.get("clientID")}`,
                protocol: "https:",
                method: "POST",
                headers: {
                    'Content-Type': ['application/json', 'application/json'],
                    'Authorization': `Bearer ${this.token}`
                }
            }, JSON.stringify(payload)).then(response => {
                const data: any = JSON.parse(response);
                if (data['status'] !== 'success') {
                    data['host'] = Configuration.get("kafkaProducerAPIHost");
                    data['path'] = Configuration.get("kafkaProducerAPIPath");
                    KSSLogger.instance.log({
                        level: KSSLogger.level.ERROR,
                        message: JSON.stringify(data)
                    });
                }
            }).catch(e => {
                KSSLogger.instance.log({
                    level: KSSLogger.level.ERROR,
                    message: JSON.stringify(e)
                });
            })
        } else {
            // TODO write to Kafka request before getting the initial token
        }
    }

    private getToken(): Promise<string> {
        return new Promise<string>((resolve, reject) => {
            const formData = querystring.stringify({
                'grant_type': 'client_credentials'
            });
            this.makeRequest({
                host: Configuration.get("oidcTokenHost"),
                path: Configuration.get("oidcTokenPath"),
                protocol: "https:",
                method: "POST",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                auth: `${Configuration.get("clientID")}:${Configuration.get("clientSecret")}`
            }, formData).then(response => {
                const data: IResponseToken = JSON.parse(response);
                if (data.error || !data.access_token) {
                    data.host = Configuration.get("oidcTokenHost");
                    data.path = Configuration.get("oidcTokenPath");
                    reject(data);
                } else {
                    const expirationDuration = data.expires_in;
                    // Refreshes the login when 90% of the lifespan of the token has elapsed
                    setTimeout(() => {
                        this.getAndSaveToken();
                    }, expirationDuration * 900);
                    resolve(data.access_token);
                }
            }).catch(e => reject(e));
        })
    }

    private makeRequest(options: https.RequestOptions, payload?: any): Promise<any> {
        return new Promise<any>((resolve, reject) => {
            const req = https.request(options, (res) => {
                let response = '';
                res.setEncoding('utf8');
                res.on('data', (chunk) => {
                    response += chunk;
                });
                res.on('end', () => {
                    resolve(response);
                });
            });

            req.on('error', (e) => {
                reject(e);
            });

            req.write(payload);
            req.end();
        });
    }
}
