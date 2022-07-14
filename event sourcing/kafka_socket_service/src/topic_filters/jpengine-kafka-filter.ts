import {KafkaFilter} from "../kafka-filter";
import { has, get, each } from "lodash";
import {JPEngineKeys} from "../interfaces/data/i-kafka-keys";

export class JPEngineKafkaFilter extends KafkaFilter {

    filter(data: any): Array<SocketIOClient.Socket> {
        let transactionType: string = get(data, JPEngineKeys.TRANSACTION_TYPE);
        let approved: boolean = get(data, [JPEngineKeys.JACKPOT_WINS, JPEngineKeys.WALLET_APPROVED]);
        if (transactionType !== 'JPE_WIN' || approved !== false) {
            return;
        }
        let socketList: Array<SocketIOClient.Socket> = [];
        const lid: string = get(data, JPEngineKeys.LICENSEE_ID);
        const playerID: string = get(data, JPEngineKeys.PLAYER_ID);

        socketList = this.getJPSocket(lid, playerID)
        return socketList;
    }

    private getJPSocket(lid: string, playerID: string): Array<SocketIOClient.Socket> {
        if (has(this._filters, [lid, playerID])) {
            return get(this._filters, [lid, playerID]);
        }
    }
}
