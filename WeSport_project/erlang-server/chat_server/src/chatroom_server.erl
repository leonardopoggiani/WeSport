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
  io:format("~p leave_cast! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State}.

handle_cast({logout, Pid}, State) ->
  io:format("~p leave_cast! ~n",[Pid]),
  quit(Pid, State),
  {noreply, State};

handle_cast({send_message_chatroom, {Pid_sender, {Sport, Sender_NickName}, Message_Text}}, State) ->
  io:format("Chatroom: ~p ~n",[Sport]),
  io:format("Send to PID sender ~p ~n",[Pid_sender]),

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

% ---------- UTILITY ------------

format_message(NickName, Message) ->
  FormattedMsg = binary_to_list(NickName) ++ ":" ++ binary_to_list(Message),
  FormattedMsg.

send_message_chatroom_member([H|T], Sender_NickName, Message_Text, Pid_sender) ->
  case [H|T] of
    [] ->
      io:format("Users finished ~n"),
      ok;
    [H|T] ->
      case H of
        [] ->
          io:format("Users finished ~n"),
          ok;
        {Pid,_} ->
          io:format("Send from ~p ~n",[Sender_NickName]),
          if
            Pid /= Pid_sender ->
              io:format("Send to PID ~p ~n",[Pid]),
              FormattedMessage = format_message(Sender_NickName, Message_Text),
              Pid ! {send_message_chatroom, Pid, FormattedMessage};
            true ->
              ok
          end,

          send_message_chatroom_member(T,Sender_NickName, Message_Text, Pid_sender)
      end
  end;

send_message_chatroom_member([], _, _, _) ->
  io:format("Users finished ~n"),
  ok.

quit(Pid, _S) ->
  send(Pid, "Goodbye ~p", [Pid]),
  io:format("~p Left the chat server! ~n", [Pid]),
  gen_server:call(?SPORT_HANDLER, {logout, Pid}).

% invia una semplice stringa, utilizzata solo per inviare messaggi generati dal server
send(Pid, Str, Args) ->
  io:format("~p send! ~n",[Str]),
  Pid ! {send_message, self(), io_lib:format(Str++"~n", Args)},
  ok.
