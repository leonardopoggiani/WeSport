-module(id_handler_server).
-behaviour(gen_server).

%% API
-include("chat.hrl").
-export([start_link/0, init/1, handle_call/3, handle_cast/2]).

start_link() ->
    gen_server:start_link({local, ?MODULE}, ?MODULE, [], []).

init(_) ->
  Users = ets:new(users_table, [set]),
  {ok, Users}.


handle_call({insert_user, NickName, Pid}, _From, Users) ->
  Response = check_and_insert_nick(NickName, Pid, Users),
  {reply, Response, Users};

handle_call({retrieve_pid, NickName}, _From, Users) ->
  Response = ets:lookup(Users, NickName),
  {reply, Response, Users};

handle_call({online_users}, _From, Users) ->
  Response = list_online_users(Users),
  {reply, Response, Users};

handle_call({logout, Pid}, _From, Users) ->
  Response = ets:match_delete(Users, {'_', Pid}),
  {reply, Response, Users};

handle_call(_Message, _From, State) ->
  {reply, error, State}.


handle_cast(_Message, State) ->
  {noreply, State}.

check_and_insert_nick(NickName, Pid, Users) ->
  case ets:insert_new(Users, {NickName, Pid}) of
    true -> ok;
    false -> nickname_in_use
  end.

list_online_users(Users) ->
  Users_list = ets:tab2list(Users),
  String_users_list = create_string_list(Users_list),
  String_users_list.

create_string_list([{NickName, _}|T], Result) ->
  create_string_list(T, Result ++ binary_to_list(NickName) ++ "|");
create_string_list([], Result) ->
  Result.

create_string_list(UsersList) ->
  create_string_list(UsersList, "").
