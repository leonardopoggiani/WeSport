var websocket;

const LOGIN = "&CHATROOM_LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";
const CHATROOM = "&CHATROOM"

var username = "";
var sport = "";

const server_url = "ws://172.18.0.60:3307";

var id_timer = null;

function print_message(sender_name, message, sendOrReceive) {

    var box = document.getElementById("chatbox");

    var div = document.createElement("div");
    div.setAttribute("class", "chatbox__messages");

    var indMessageDiv = document.createElement("div");

    var p_name = document.createElement("p");
    p_name.setAttribute("class", "name");

    var p_message = document.createElement("p");
    p_message.setAttribute("class", "message");

    if(sendOrReceive == false) {
        console.log("SPORT: " + sport);
        //messaggio inviato

        indMessageDiv.setAttribute("class", "chatbox__messages__user-message--ind-message__right");

        p_name.textContent = "Sent to " + sport + " chat:";
        p_message.textContent = message;
    } else {
        // messaggio da un altro utente
        p_name.textContent = "From " + sender_name + ":";
        p_message.textContent = message;

        indMessageDiv.setAttribute("class", "chatbox__messages__user-message--ind-message__left");

    }

    indMessageDiv.appendChild(p_name);
    indMessageDiv.appendChild(document.createElement("br"));
    indMessageDiv.appendChild(p_message);
    div.appendChild(indMessageDiv);
    box.appendChild(div);
}

// WEBSOCKET

function keep_connection_alive(){
    const timeout = 20000;
    if(websocket.readyState === websocket.OPEN)
        websocket.send(PING);
    id_timer = setTimeout(keep_connection_alive, timeout);
}

function stop_keep_alive(){
    clearTimeout(id_timer);
}

function ws_onOpen() {
    websocket.send(LOGIN + ":" + sport);
    keep_connection_alive();
}

function ws_onClose() {

}

function ws_onMessage(event) {

    console.log("MESSAGE: " + event.data);

    var message_fields = event.data.split(':');

    if(message_fields.length === 2){
        //normale messaggio inviato da un altro utente
        if(message_fields[0] != username) {
            print_message(message_fields[0], message_fields[1], true);
        }
    }
}

function change_sport() {
    var sport_selected = document.getElementById("sports");
    sport = sport_selected.options[sport_selected.selectedIndex].text;

    connect(username);
}

//logging_user is the username of the user that is entering in the chat page
function connect(logging_user){

    username = logging_user;

    var sport_selected = document.getElementById("sports");
    sport = sport_selected.options[sport_selected.selectedIndex].text;

    websocket = new WebSocket(server_url);
    websocket.onopen = function(){ws_onOpen()};
    websocket.onclose = function(){ws_onClose()};
    websocket.onmessage = function(event){ws_onMessage(event)};
}

function disconnect(){
    websocket.send(LOGOUT);
    websocket.close();
    stop_keep_alive();
}

function send_message(event){

    var input_message = document.getElementById("message_text");

    var message_text = input_message.value;
    input_message.value = "";

    websocket.send(CHATROOM + ":" + message_text + ":" + username + ":" + sport);
    print_message(username, message_text, false);
}
