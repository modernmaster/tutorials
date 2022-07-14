import {KafkaFilter} from "../kafka-filter";
import {each, get, has} from "lodash";
import {IFilterMapping} from "../interfaces/i-filter-mapping";

export class CLPOCKafkaFilter extends KafkaFilter {

    filter(data: any): Array<SocketIOClient.Socket> {
        let keyPath: Array<string> = [];
        each(this._filteredKeys, (filterMap: IFilterMapping) => {
            keyPath.push(data[filterMap.dataKeyName]);
        });
        if (has(this._filters, keyPath)) {
            return [...get(this._filters, keyPath)];
        }
    }
}
