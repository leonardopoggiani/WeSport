-module(message_server).
-behaviour(gen_server).

%% API
-export([start_link/0, init/1, handle_cast/2, handle_call/3, format_message/2]).
-include("chat.hrl").


start_link() ->
  gen_server:start_link({local, ?MODULE}, ?MODULE, [], []).


init(_) ->
  Dispatch = cowboy_router:compile([
    {'_', [{'_', websocket_handler, #{}}]}
  ]),
  cowboy:start_clear(my_http_listener,
    [{port, 3307}],
    #{env=> #{dispatch =>Dispatch}}
  ),
  {ok, ""}.


handle_call({logout, Pid}, _From, State) ->
  io:format("~p leave_cast! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State}.


handle_cast({logout, Pid}, State) ->
  io:format("~p leave_cast! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State};

handle_cast({send_message, {Pid_sender, {Receiver_NickName, Sender_NickName}, Message_Text}}, State) ->
  case gen_server:call(?ID_HANDLER, {retrieve_pid, Receiver_NickName}) of
    [] ->
      Pid_sender ! {send_message, Pid_sender,"User not available."};
    [{_,Pid}] ->
      FormattedMessage = format_message(Sender_NickName, Message_Text),
      Pid ! {send_message, Pid_sender, FormattedMessage}
  end,
  {noreply, State};

handle_cast({login, {Pid, NickName}}, State) ->
  Reply = gen_server:call(?ID_HANDLER, {insert_user, NickName, Pid}),
  case Reply of
    nickname_in_use ->
      send(Pid, "The chosen NickName is already in use", []);
    ok ->
      send(Pid, "~p Welcome in the chat!", [binary_to_list(NickName)])
  end,
  {noreply, State};

handle_cast({online_users, Pid}, State) ->
  Response = gen_server:call(?ID_HANDLER, {online_users}),
  send(Pid, Response, []),
  {noreply, State}.


quit(Pid, _S) ->
  send(Pid, "Goodbye ~p", [Pid]),
  io:format("~p Left the chat server! ~n", [Pid]),
  gen_server:call(?ID_HANDLER, {logout, Pid}).


% invia una semplice stringa, utilizzata solo per inviare messaggi generati dal server
send(Pid, Str, Args) ->
  io:format("~p send! ~n",[Str]),
  Pid ! {send_message, self(), io_lib:format(Str++"~n", Args)},
  ok.

% ---------- UTILITY ------------

format_message(NickName, Message) ->
  FormattedMsg = binary_to_list(NickName) ++ ":" ++ binary_to_list(Message),
  FormattedMsg.

%format_time() ->
%  {Mega, Sec, Micro} = os:timestamp(),
%  io_lib:format('(~13..0b)', [(Mega*1000000 + Sec)*1000 + round(Micro/1000)]).

%to_string(Value) when is_binary(Value) -> binary_to_list(Value);
%to_string(Value) -> Value.

%concat(List) ->
%  lists:flatten(lists:map(fun to_string/1, List)).
