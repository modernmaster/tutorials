import {ITopicName} from "./i-topic-name";

export interface IConfigurationFeed {
    consumers: ITopicName[]
}

export interface IConsumerConfig {
    topic: string
}
