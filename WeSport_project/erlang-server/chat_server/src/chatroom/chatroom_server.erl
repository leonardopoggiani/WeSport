-module(chatroom_server).
-behaviour(gen_server).

%% API
-export([start_link/0, init/1, handle_cast/2, handle_call/3, format_message/2]).
-include("chat.hrl").


start_link() ->
  gen_server:start_link({local, ?MODULE}, ?MODULE, [], []).


init(_) ->
  Dispatch = cowboy_router:compile([
    {'_', [{'_', chatroom_websocket_handler, #{}}]}
  ]),
  cowboy:start_clear(chatroom_listener,
    [{port, 3307}],
    #{env=> #{dispatch =>Dispatch}}
  ),
  {ok, ""}.

handle_call({logout, Pid}, _From, State) ->
  io:format("~p leave_call! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State}.

handle_cast({logout, Pid}, State) ->
  io:format("~p leave_cast! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State};

handle_cast({send_message_chatroom, {Pid_sender, {Sport, Sender_NickName}, Message_Text}}, State) ->

  case gen_server:call(?SPORT_HANDLER, {retrieve_pid, Sport}) of
    [] ->
      Pid_sender ! {send_message_chatroom, Pid_sender,"Sport not available."};
    [H|T] ->
      send_message_chatroom_member([H|T], Sender_NickName, Message_Text, Pid_sender)
  end,
  {noreply, State};

handle_cast({login, {Pid, Sport}}, State) ->
  gen_server:call(?SPORT_HANDLER, {insert_sport, Sport, Pid}),
  {noreply, State}.

format_message(NickName, Message) ->
  FormattedMsg = binary_to_list(NickName) ++ ":" ++ binary_to_list(Message),
  FormattedMsg.

send_message_chatroom_member([H|T], Sender_NickName, Message_Text, Pid_sender) ->
  case H of
    [] ->
      ok;
    {Pid,_} ->
      if
        Pid /= Pid_sender ->
          FormattedMessage = format_message(Sender_NickName, Message_Text),
          Pid ! {send_message_chatroom, Pid, FormattedMessage};
        true ->
          ok
      end,

      send_message_chatroom_member(T,Sender_NickName, Message_Text, Pid_sender)
  end;

send_message_chatroom_member([], _, _, _) ->
  ok.

quit(Pid, _S) ->
  send(Pid, "Goodbye ~p", [Pid]),
  io:format("~p left the chatroom! ~n", [Pid]),
  gen_server:call(?SPORT_HANDLER, {logout, Pid}).

send(Pid, Str, Args) ->
  io:format("~p send! ~n",[Str]),
  Pid ! {send_message, self(), io_lib:format(Str++"~n", Args)},
  ok.
