var websocket;

const LOGIN = "&LOGIN";
const LOGOUT = "&LOGOUT";
const PING = "&PING";

var username = "";

const server_url = "ws://127.0.0.1:3307";

var id_timer = null;

// UTILITY