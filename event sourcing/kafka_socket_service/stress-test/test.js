function connectWalletSocket() {
    const socket = require('socket.io-client')('http://localhost:3012');
    socket.emit('register', {
        topic: "wallet_transaction",
        playerID: "Ashden",
        skinID: 200241,
        lid: 628
    });
    socket.on('feed', function (data) {
    });
}

function connectJPBOSocket() {
    const socket = require('socket.io-client')('http://localhost:3012');
    socket.emit('register', {
        topic: "jackpot_backoffice_realtime",
        engineId: [8575, 8578, 8581, 8584],
        lid: 628
    });
    socket.on('feed', function (data) {
    });
    socket.on('win', function (data) {
    });
}

for (let i = 0; i < 3000; i++) {
    connectWalletSocket();
}
setTimeout(() => {
    for (let i = 0; i < 3000; i++) {
        connectJPBOSocket();
    }
}, 3000);
// console.log(socket);
// process.exit(0);
