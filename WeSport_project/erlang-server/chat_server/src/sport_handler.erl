-module(sport_handler).
-behaviour(gen_server).

%% API
-include("chat.hrl").
-export([start_link/0, init/1, handle_call/3, handle_cast/2]).

start_link() ->
    gen_server:start_link({local, sport_handler}, sport_handler, [], []).

init(_) ->
  Sports = ets:new(sports_table, [set]),
  {ok,Sports}.

handle_call({insert_sport, Sport, Pid}, _From, Sports) ->
    Response = check_and_insert_sport(Sport, Pid, Sports),
    io:format("PID Sport inserito: ~p ~n",[Pid]),
    {reply, Response, Sports};

handle_call({retrieve_pid, Sport}, _From, Sports) ->
  io:format("Sport cercato: ~p ~n",[Sport]),
  All_table = ets:match_object(Sports, {'$0', '$1'}),
  io:format("all_table: ~p ~n",[All_table]),
  Response = ets:match_object(Sports, {'_', Sport}),
  io:format("Response: ~p ~n",[Response]),
  {reply, Response, Sports};

  handle_call({logout, Pid}, _From, Sports) ->
    io:format("Elimino: ~p ~n",[Pid]),
    Response = ets:match_delete(Sports, {Pid, '_'}),
    {reply, Response, Sports};

handle_call(_Message, _From, State) ->
  {reply, error, State}.

handle_cast(_Message, State) ->
  {noreply, State}.

check_and_insert_sport(Sport, Pid, Sports) ->
  case ets:insert_new(Sports, {Pid, Sport}) of
    true -> ok;
    false -> sport_already_inserted
  end.
