-module(chatroom_websocket_handler).

%% API
-export([init/2, websocket_handle/2, terminate/3, websocket_info/2]).
-include("chat.hrl").

init(Req, Opts) ->
  {cowboy_websocket, Req, Opts}.

websocket_handle({text, Message}, State) ->
  Param_list = string:lexemes(Message, ":"),   % scompongo la stringa in arrivo in una lista di stringhe

  io:format("Param_list: ~p ~n",[Param_list]),

  First_param = hd(Param_list),
  Last_param = lists:last(Param_list),

  if
    First_param == <<"&CHATROOM_LOGIN">> ->
      Sport = Last_param,
      gen_server:cast(?CHATROOM_SERVER, {login, {self(), Sport}});

    First_param == <<"&PING">> ->
      gen_server:cast(?CHAT_SERVER, {online_users, self()});

    First_param == <<"&LOGOUT">> ->
      io:format("LOGOUT ~n"),
      gen_server:cast(?CHATROOM_SERVER, {logout, self()});

    true ->
      Chatroom_message = lists:nth(2, Param_list),
      io:format("Message: ~p ~n",[Chatroom_message]),
      Sender = lists:nth(3, Param_list),
      io:format("Sender: ~p ~n",[Sender]),
      Sport = lists:nth(4, Param_list),
      io:format("Sport: ~p ~n",[Sport]),
      gen_server:cast(?CHATROOM_SERVER, {send_message_chatroom, {self(), {Sport, Sender}, Chatroom_message}})
  end,
  {ok, State};

websocket_handle(_Data, State) ->
  {[], State}.

websocket_info({send_message_chatroom, _ServerPid, Msg}, State) ->
  {reply, {text, Msg}, State};

websocket_info(_Info, State) ->
  {[], State}.

terminate(_Reason, _Req, _State) ->
  gen_server:cast(?CHATROOM_SERVER, {logout, self()}),
  ok.
