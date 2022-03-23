var websocket;

const LOGIN = "&LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";

var username = "";

const server_url = "ws://172.18.0.36:3307";

var id_timer = null;

function keep_connection_alive(){
    const timeout = 20000;
    if(websocket.readyState === websocket.OPEN)
        websocket.send(PING);
    id_timer = setTimeout(keep_connection_alive, timeout);
}

function ws_onOpen() {
    websocket.send(LOGIN + ":" + username);
    keep_connection_alive();
}

function ws_onClose() {

}

function stop_keep_alive(){
    clearTimeout(id_timer);
}


//logging_user is the username of the user that is entering in the chat page
function connect(logging_user){
    username = logging_user;
    websocket = new WebSocket(server_url);
    websocket.onopen = function(){ws_onOpen()};
    websocket.onclose = function(){ws_onClose()};
    // websocket.onmessage = function(event){ws_onMessage(event)};
}

function disconnect(){
    websocket.send(LOGOUT);
    websocket.close();
    stop_keep_alive();
}
