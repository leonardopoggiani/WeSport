# Istruzione per avviare il server chat Erlang

- Spostarsi nella cartella chat_server/
- `rebar3 compile` per compilare i file di Erlang
- `rebar3 shell` per eseguire la shell di Erlang giá avviata con le dipendenze necessarie (_cowboy_)
- Dalla shell `start_chat:start(opt,opt).`
