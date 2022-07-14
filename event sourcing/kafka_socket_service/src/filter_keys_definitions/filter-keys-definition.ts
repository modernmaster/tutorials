import {each, isArray, takeRight} from "lodash";
import {cartesianProduct} from "../kss-consumer";

export class FilterKeysDefinition {
    getKeyPaths(data: { [p: string]: string | Array<string> | object | number }, registrationKeys: Array<string>): Array<Array<string>> {
        let result;
        /**
         * arraysOfValues is a multidimensional array containing the values of the filters for this particular widget.
         * For instance, [["237"], ["player1"]] for wallet, or
         * [
         *   [740, 741],
         *   [939, 940]
         * ]
         * in the case of a potential JPEngine widget.
         */
        let arraysOfValues: Array<Array<string>> = [];
        each(registrationKeys, (registrationKey: string) => {
            if (!isArray(data[registrationKey])) {
                data[registrationKey] = [data[registrationKey] as string];
            }
            arraysOfValues.push((data[registrationKey] as string[]).map(String));
        });
        /**
         * result will be a multidimensional array, containing the cartesian product of the arrays of values from
         * the widget. Following the earlier example, the result will be:
         * [
         *   [740, 939],
         *   [740, 940],
         *   [741, 939],
         *   [741, 940]
         * ]
         */
        if (registrationKeys.length > 2) {
            result = cartesianProduct(arraysOfValues[0], arraysOfValues[1], ...takeRight(arraysOfValues, arraysOfValues.length - 2));
        } else {
            result = cartesianProduct(arraysOfValues[0], arraysOfValues[1]);
        }
        return result;
    }
}
