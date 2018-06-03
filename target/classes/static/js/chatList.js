
var div=document.getElementsByTagName("p");
var paragrafid;


function start() {


    for (i = 0; i < div.length; i++) {
        div[i].addEventListener("click", function (ev) {
            paragrafid = ev.currentTarget.id;
            window.location.href="/chat/messaging/"+paragrafid;
        });
    }
}
function logout( ) {
    window.location.href="/logout";


}