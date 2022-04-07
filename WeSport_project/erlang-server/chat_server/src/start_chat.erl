-module(start_chat).
-behaviour(application).

%% API
-export([start/2, stop/1]).


start(_Type, _Args) ->
  {ok, _ControllerPid} = user_handler:start_link(),
  {ok, ServerPid} = chat_server:start_link(),
  sport_handler:start_link(),
  chatroom_server:start_link(),
  io:fwrite("Chat server started successfully!~n"),
  {ok, ServerPid}.

stop(_State) ->
  ok = cowboy:stop_listener(chat_listener),
  ok = cowboy:stop_listener(chatroom_listener).
