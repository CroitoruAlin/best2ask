

var user=document.getElementById("username1");
var paragrafid=document.getElementById("username2");
var i;
var ws=null;
function start() {
    connect();
    rezolvaMesaje();

}
function start1() {

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

var socket;

function sendMessage() {
    var textarea=document.getElementById("mesaje");
    var mesajContinut= textarea.value.trim();
    if(mesajContinut && ws) {
        var chatMessage = {
            username: paragrafid.innerHTML,
            content: textarea.value


        }

        ws.send("/app/queue/"+user.innerHTML+"/"+paragrafid.innerHTML, {}, JSON.stringify(chatMessage));
        showMessage1(textarea.value)
    }
    textarea.value="";
}
function showMessage1(mesaj) {
    $("#conv").append(" " + user.innerHTML+": "+mesaj + "\n");

}
function showMessage(message) {
    var parsare=JSON.parse(message.body);
    $("#conv").append(" " + parsare.name+": "+parsare.content + "\n");
}
function logout( ) {
    window.location.href="/logout";

}
function rezolvaMesaje() {
    var autori=document.getElementsByClassName("autori");
    var mesaje=document.getElementsByClassName("mesaje");
    for(var i=0;i<autori.length;i++)
        showMessage2(autori[i].id,mesaje[i].id);

}
function showMessage2(autor,mesaj) {
    $("#conv").append(" " + autor+": "+mesaj +"\n");

}