import {Dictionary} from "lodash";
import {IFilterMapping} from "./interfaces/i-filter-mapping";

export class KafkaFilter {
    protected _filters: Dictionary<any>;
    protected _filteredKeys: Array<IFilterMapping>;

    constructor(filters: Dictionary<any>, filteredKeys: Array<IFilterMapping>) {
        this._filters = filters;
        this._filteredKeys = filteredKeys;
    }
    filter(data: any): Array<SocketIOClient.Socket> {
        return;
    }
}
