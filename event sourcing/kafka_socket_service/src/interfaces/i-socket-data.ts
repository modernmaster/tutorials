export interface ISocketData {
    data: {
        [key: string]: string | Array<string>
    },
    socket: SocketIOClient.Socket
}
