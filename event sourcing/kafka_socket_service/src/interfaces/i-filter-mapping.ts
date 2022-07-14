export interface IFilterMapping {
    dataKeyName: string, // player_id, licensee_id, etc - the key name in Kafka record
    registrationKey: string // playerID, lid - name of the key in the widget registration
}
