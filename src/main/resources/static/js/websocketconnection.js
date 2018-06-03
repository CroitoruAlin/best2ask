function start() {
    connect();
}
function connect() {
    socket = new SockJS("/ws");

    ws = Stomp.over(socket);

    ws.connect({},onConnected);
}
function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}
function onConnected() {
    ws.subscribe("/user/queue/chat.message",function (message) {
        showMessage(message);
    });
}
function showMessage(message) {
    $("#conv").append(" " + message.getContent() + "");
}