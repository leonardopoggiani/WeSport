-module(websocket_handler).

%% API
-export([init/2, websocket_handle/2, terminate/3, websocket_info/2]).
-include("chat.hrl").

init(Req, Opts) ->
  {cowboy_websocket, Req, Opts}.

websocket_handle({text, Message}, State) ->
  io:format("message: ~p ~n",[Message]),

  Param_list = string:lexemes(Message, ":"),   % scompongo la stringa in arrivo in una lista di stringhe

  io:format("Param_list: ~p ~n",[Param_list]),
  % example -> Param_list: [<<"&CHATROOM">>,<<"ciao">>,<<"wiza.julia">>,<<"Tennis">>]

  First_param = hd(Param_list),
  Last_param = lists:last(Param_list),

  if
    First_param == <<"&LOGIN">> ->
      % L'utente ha fatto l'accesso alla pagina di chat
      NickName = Last_param,
      gen_server:cast(?SERVER, {login, {self(), NickName}});

    First_param == <<"&LOGOUT">> ->
      % L'utente non vuole piu chattare
      gen_server:cast(?SERVER, {logout, self()});

    First_param == <<"&PING">> ->
      gen_server:cast(?SERVER, {online_users, self()});

    First_param == <<"&CHATROOM">> ->
      Chatroom_message = lists:nth(2, Param_list),
      io:format("Message: ~p ~n",[Chatroom_message]),
      Sender = lists:nth(3, Param_list),
      io:format("Sender: ~p ~n",[Sender]),
      Sport = lists:nth(4, Param_list),
      io:format("Sport: ~p ~n",[Sport]),
      gen_server:cast(?SERVER, {send_message_chatroom, {self(), {Sport, Sender}, Chatroom_message}});

    true ->
      % L'utente ha inviato un messaggio
      MessageText = First_param,
      % l'ultimo parametro è il destinatario del messaggio
      Receiver_NickName = Last_param,
      % il secondo parametro è il mittente
      Sender_NickName = lists:nth(2, Param_list),
      gen_server:cast(?SERVER, {send_message, {self(), {Receiver_NickName, Sender_NickName}, MessageText}})
  end,
  {ok, State};
websocket_handle(_Data, State) ->
  {[], State}.

websocket_info({send_message, _ServerPid, Msg}, State) ->
  {reply, {text, Msg}, State};

websocket_info(_Info, State) ->
  {[], State}.

terminate(_Reason, _Req, _State) ->
  gen_server:cast(?SERVER, {logout, self()}),
  ok.
