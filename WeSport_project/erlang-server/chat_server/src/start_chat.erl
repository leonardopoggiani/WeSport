-module(start_chat).
-behaviour(application).

%% API
-export([start/2, stop/1]).


start(_Type, _Args) ->
  {ok, _ControllerPid} = id_handler_server:start_link(),
  {ok, ServerPid} = message_server:start_link(),
  io:fwrite("Chat server started successfully!~n"),
  {ok, ServerPid}.

stop(_State) ->
  ok = cowboy:stop_listener(my_http_listener).
