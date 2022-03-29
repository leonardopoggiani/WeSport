-module(sport_handler).
-behaviour(gen_server).

%% API
-include("chat.hrl").
-export([start_link/0, init/1, handle_call/3, handle_cast/2]).

start_link() ->
    io:format("Start link! ~n"),
    gen_server:start_link({local, sport_handler}, sport_handler, [], []).

init(_) ->
  Sports = ets:new(sports_table, [set]),
  {ok,Sports}.

handle_call({insert_sport, Sport, Pid}, _From, Sports) ->
    io:format("Sport inserito: ~p ~n",[Sport]),
    Response = check_and_insert_sport(Sport, Pid, Sports),
    {reply, Response, Sports};

handle_call({retrieve_pid, Sport}, _From, Sports) ->
  io:format("Sport cercato: ~p ~n",[Sport]),
  SportToSearch = "Tennis",
  Response = ets:lookup(Sports, SportToSearch),
  io:format("Response: ~p ~n",[Response]),
  {reply, Response, Sports};

handle_call(_Message, _From, State) ->
  {reply, error, State}.

handle_cast(_Message, State) ->
  {noreply, State}.

check_and_insert_sport(Sport, Pid, Sports) ->
case ets:insert_new(Sports, {Sport, Pid}) of
    true -> ok;
    false -> sport_already_inserted
end.


% start_chat:start(opt,opt).
