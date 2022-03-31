# Istruzioni per avviare il server chat Erlang

- Spostarsi nella cartella chat_server/
- `rebar3 compile` per compilare i file di Erlang
- `rebar3 shell` per eseguire la shell di Erlang giá avviata con le dipendenze necessarie (_cowboy_)
- Dalla shell `start_chat:start(opt,opt).`

Se viene segnalato un problema con **rebar3** é perché non si é installato adeguatamente, si puó usare la versione giá compilata presente in _WeSport/_ con ./path-to-WeSport/Wesport/rebar3 e i comandi indicati sopra.
Per sistemi Ubuntu (e distro simili) é possibile definire un alias in .bashrc (a seconda della shell che utilizzate) in questo modo:
`alias rebar3="./path-to-WeSport/Wesport/rebar3"` e poi mandare `source .bashrc` per poter utilizzare la versione abbreviata del comando.
