export interface IKafkaMessage {
    "topic": string,
    "value": string,
    "offset": number,
    "partition": number,
    "highWaterOffset": number,
    "key": string,
    "timestamp": string
}
