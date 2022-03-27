var websocket;

const LOGIN = "&LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";

var username = "";

const server_url = "ws://localhost:3307";

var id_timer = null;

function print_message(sender_name, message, receiver) {

    var box = document.getElementById("box");

    var messageDiv = document.createElement("div");

    var p_name = document.createElement("p");
    var p_name_text;
    var p_message = document.createElement("p");
    var p_message_text;

    if(receiver != null) {
        //messaggio inviato
        messageDiv.setAttribute("class", "chat-right-message");
        p_name_text = document.createTextNode("Sent to " + receiver + ":");
        p_message_text = document.createTextNode(message);
    } else {
        //messaggio in arrivo
        if (sender_name == null) {
            // messaggio inviato dal server
            console.log(message);
            return;
        } else {
            // messaggio da un altro utente
            messageDiv.setAttribute("class", "chat-left-message");
            p_name_text = document.createTextNode("From: " + sender_name);
            p_message_text = document.createTextNode(message);
            var receiver_tag = document.getElementById("receiver");
            receiver_tag.textContent = sender_name;

        }
    }

    p_name.appendChild(p_name_text);
    p_message.appendChild(p_message_text);
    messageDiv.appendChild(p_name);
    messageDiv.appendChild(document.createElement("br"));
    messageDiv.appendChild(p_message);
    box.appendChild(messageDiv);
}

function update_online_users(users_list) {

    var all_users_list = document.getElementsByName("chatbox_user");

    for(var i = 0; i < all_users_list.length; i++) {
        for (var j = 0; j < users_list.length - 1; j++) {
            if (all_users_list[i].id == users_list[j]) {
                var online_user = document.getElementById("icon-" + all_users_list[i].id);
                online_user.setAttribute("class", "fa-solid fa-comment");

                var online_user_box = document.getElementById(all_users_list[i].id);
                online_user_box.setAttribute("class", "user-online");

                var div_online_user = document.getElementById("div-" + all_users_list[i].id);
                div_online_user.onclick = set_chat_receiver;
                break;
            } else {
                var offline_user = document.getElementById("icon-" + all_users_list[i].id);
                offline_user.setAttribute("class", "fa-solid fa-comment-slash");
            }
        }
    }
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
    websocket.send(LOGIN + ":" + username);
    keep_connection_alive();
}

function ws_onClose() {

}

function ws_onMessage(event) {
    var message_fields = event.data.split(':');
    if(message_fields.length === 2){
        //normale messaggio inviato da un altro utente
        print_message(message_fields[0], message_fields[1]);
    } else {
        message_fields = event.data.split('|');
        if(message_fields.length > 1){
            //the new online users list is arrived
            update_online_users(message_fields);
        } else {
            // semplice stringa di risposta
            print_message(null, event.data);
        }
    }
}

//logging_user is the username of the user that is entering in the chat page
function connect(logging_user){
    console.log("CONNECT");
    username = logging_user;
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
    console.log("MESSAGE: " + message_text);

    const receiver_username = document.getElementById("receiver").textContent;
    console.log("RECEIVER: " + receiver_username);

    if(receiver_username != "" || receiver_username != "no one actually :(") {
        if (message_text != "") {
            websocket.send(message_text + ":" + username + ":" + receiver_username);
            print_message(username, message_text, receiver_username);
        }
    }
}

function set_chat_receiver(event) {

    var splitted = event.target.id.split("-");
    var id = "";

    if(splitted.length > 1 && splitted[0] == "icon") {
        id = splitted[1];
    } else {
        id = event.target.id;
    }

    if(event.target.id == null) {
        document.getElementById("receiver").textContent = "No one actually :(";
    } else {
        document.getElementById("receiver").textContent = id;
    }
}