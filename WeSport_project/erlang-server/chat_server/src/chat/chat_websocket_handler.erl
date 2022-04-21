-module(chat_websocket_handler).

%% API
-export([init/2, websocket_handle/2, terminate/3, websocket_info/2]).
-include("chat.hrl").

init(Req, Opts) ->
  {cowboy_websocket, Req, Opts}.

websocket_handle({text, Message}, State) ->
  Param_list = string:lexemes(Message, ":"), 
  First_param = hd(Param_list),
  Last_param = lists:last(Param_list),

  if
    First_param == <<"&LOGIN">> ->
      NickName = Last_param,
      gen_server:cast(?CHAT_SERVER, {login, {self(), NickName}});

    First_param == <<"&LOGOUT">> ->
      gen_server:cast(?CHAT_SERVER, {logout, self()});

    First_param == <<"&PING">> ->
      gen_server:cast(?CHAT_SERVER, {online_users, self()});

    true ->
      MessageText = First_param,
      Receiver_NickName = Last_param,
      Sender_NickName = lists:nth(2, Param_list),
      gen_server:cast(?CHAT_SERVER, {send_message, {self(), {Receiver_NickName, Sender_NickName}, MessageText}})
  end,
  {ok, State};
  
websocket_handle(_Data, State) ->
  {[], State}.

websocket_info({send_message, _ServerPid, Msg}, State) ->
  {reply, {text, Msg}, State};

websocket_info(_Info, State) ->
  {[], State}.

terminate(_Reason, _Req, _State) ->
  gen_server:cast(?CHAT_SERVER, {logout, self()}),
  ok.
