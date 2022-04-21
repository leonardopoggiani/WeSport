var websocket;

const LOGIN = "&LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";

var username = "";

const server_url = "ws://172.18.0.60:3308";

var id_timer = null;

function print_message(sender_name, message, receiver) {

    var box = document.getElementById("chatbox");

    var div = document.createElement("div");
    div.setAttribute("class", "chatbox__messages");

    var indMessageDiv = document.createElement("div");

    var p_name = document.createElement("p");
    p_name.setAttribute("class", "name");

    var p_message = document.createElement("p");
    p_message.setAttribute("class", "message");

    if(receiver != null) {
        console.log("RECEIVER: " + receiver);
        //messaggio inviato

        if(receiver.split(" ").length > 1) {
            console.log("RETURN");
            return;
        }

        indMessageDiv.setAttribute("class", "chatbox__messages__user-message--ind-message__right");

        p_name.textContent = "Sent to " + receiver + ":";
        p_message.textContent = message;
    } else {
        //messaggio in arrivo
        if (sender_name == null) {
            // messaggio inviato dal server
            console.log(message);
            return;
        } else {
            // messaggio da un altro utente
            p_name.textContent = "From " + sender_name + ":";
            p_message.textContent = message;

            indMessageDiv.setAttribute("class", "chatbox__messages__user-message--ind-message__left");

            var receiver_tag = document.getElementById("receiver");
            if(receiver_tag.textContent == "" || receiver_tag.textContent == "no one actually :(") {
                receiver_tag.textContent = sender_name;
            } else if(receiver_tag.textContent != sender_name) {
                receiver_tag.textContent = sender_name;
            }

        }
    }

    indMessageDiv.appendChild(p_name);
    indMessageDiv.appendChild(document.createElement("br"));
    indMessageDiv.appendChild(p_message);
    div.appendChild(indMessageDiv);
    box.appendChild(div);
}

function update_online_users(users_list) {

    var all_users_list = document.getElementsByName("chatbox_user");

    for(var i = 0; i < all_users_list.length; i++) {
        for (var j = 0; j < users_list.length - 1; j++) {
            if (all_users_list[i].id == users_list[j]) {
                console.log("ONLINE" + all_users_list[i].id);

                var online_user = document.getElementById("div-" + all_users_list[i].id);
                online_user.setAttribute("class", "chatbox__user--active");
                online_user.onclick = set_chat_receiver;
                break;
            } else {
                var offline_user = document.getElementById("div-" + all_users_list[i].id);
                offline_user.setAttribute("class", "chatbox__user--busy");
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
        print_message(message_fields[0], message_fields[1]);
    } else {
        message_fields = event.data.split('|');
        if(message_fields.length > 1){
            update_online_users(message_fields);
        } else {
            print_message(null, event.data);
        }
    }
}

function connect(logging_user){
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
