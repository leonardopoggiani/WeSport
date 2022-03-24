var websocket;

const LOGIN = "&LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";

var username = "";

const server_url = "ws://localhost:3307";

var id_timer = null;

function print_message(sender_name, message, receiver = null) {
    console.log("PRINT MESSAGE");

    /*

    var externDiv = document.createElement("div");
    externDiv.setAttribute("class", "chatbox__messages__user-message");
    //externDiv.style.backgroundColor = "orange";
    //externDiv.style.background = "orange";
    var messageDiv = document.createElement("div");
    var p_name = document.createElement("p");
    var p_name_text;
    var p_message = document.createElement("p");
    var p_message_text;
    if(receiver != null) {
        //messaggio inviato
        messageDiv.setAttribute("class", "chatbox__messages__user-message--right-message");
        p_name_text = document.createTextNode("Sent to " + receiver + ":");
        p_message_text = document.createTextNode(message);
    } else {
        //messaggio in arrivo
        if (sender_name == null) {
            // messaggio inviato dal server
            messageDiv.setAttribute("class", "chatbox__messages__user-message--server-message");
            p_name_text = document.createTextNode("From: System");
            p_message_text = document.createTextNode(message);
        } else {
            // messaggio da un altro utente
            messageDiv.setAttribute("class", "chatbox__messages__user-message--left-message");
            p_name_text = document.createTextNode("From: " + sender_name);
            p_message_text = document.createTextNode(message);
        }
    }
    p_name.appendChild(p_name_text);
    p_message.appendChild(p_message_text);
    messageDiv.appendChild(p_name);
    messageDiv.appendChild(document.createElement("br"));
    messageDiv.appendChild(p_message);
    externDiv.appendChild(messageDiv);
    document.getElementById("message_box").appendChild(externDiv);

     */
}

function update_online_users(users_list) {
    console.log("UPDATE ONLINE USERS");

    var all_users_list = document.getElementsByName("chatbox_user");
    for(var i = 0; i < all_users_list.length; i++){
        for(var j = 0; users_list.length - 1; j++) {
            if(users_list[i] == username) {
                continue;
            }

            var online_user = document.getElementById("icon-" + all_users_list[i]);

            if(all_users_list[i] == users_list[j]) {
                online_user.setAttribute("class", "fa-solid fa-comment");
            } else {
                online_user.setAttribute("class", "fa-solid fa-comment-slash");
            }
        }
    }

    /*
    //insert new list
    var all_users_list = document.getElementsByName("chatbox_user");
    for(var i = 0; i < all_users_list.length; i++){
        all_users_list[i].setAttribute("class", "chatbox__user--busy");
    }
    var option;
    var option_text;
    for(var i = 0; i < users_list.length - 1; ++i){
        if(users_list[i] === username)
            continue;	//the logged user can not send a message to himself!
        option = document.createElement("option");
        option.setAttribute("id", users_list[i]);
        option.setAttribute("value", users_list[i]);
        option.setAttribute("class", "online_user");
        if(previous_selected === users_list[i]){
            // the selcted user is still online
            option.selected = "selected";
            previous_selected_is_online = true;
        }
        option_text = document.createTextNode(users_list[i]);
        option.append(option_text);
        select_block.append(option);
        //update online users in the list of all the users
        document.getElementById(users_list[i]).setAttribute("class", "chatbox__user--active");
    }
    if(!previous_selected_is_online)
        document.getElementById("placeholder").selected = "selected";

     */
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
    var keycode = event.keyCode;
    if(keycode != 13){
        return true;
    }
    var message_text = document.getElementById("text_input").value;
    const receiver_index = document.getElementById("select_receiver").selectedIndex;
    const receiver_username = document.getElementById("select_receiver").options[receiver_index].value;
    if(receiver_username == "choose-one") {
        //the user has not selected a receiver
        print_message(null, "You must select a receiver!", null);
        //document.getElementById("text_input").value = "";
        return false;
    }
    if(message_text == ""){
        print_message(null, "You must write something!", null);
        document.getElementById("text_input").value = "";
        return false;
    }
    websocket.send(message_text + ":" + username + ":" + receiver_username);
    print_message(null, message_text, receiver_username);
    document.getElementById("text_input").value = "";
    return false;
}

function set_chat_receiver(event) {
    console.log("SET CHAT RECEIVER: " + event.target.id);

    var x = document.getElementById("receiver").textContent;
    console.log("Actual receiver: " + x);

    if(event.target.id == null) {
        document.getElementById("receiver").textContent = "No one actually :(";
    } else {
        document.getElementById("receiver").textContent = event.target.id;
    }

    var y = document.getElementById("receiver").textContent;
    console.log("New receiver: " + y);
}