# WeSport
Repository for the Distributed Systems and Middleware Technologies UNIPI.

## Istruzioni per l'esecuzione

Connettersi alla vpn _studenti.ovpn_:

`sudo ovpn studenti.ovpn`

`ssh root@172.18.0.24`

I log del server **Glassfish** si trovano in:

*servers/glassfish5/glassfish/domains/domain1/logs/server.log*

Si puó usare un comando come *cat server.log* (file completo) oppure *tail -f -n100 server.log* (ultime 100 righe) per visualizzare i log.

Per eseguire una run in locale é necessario scaricare e installare **Glassfish 5**. Successivamente va aggiunto **Glassfish** come *application server*
su Intellij Idea (File -> Settings -> Build, Execution, Deployment -> Application Servers).

Le configurazioni per la run in remoto e in locale si trovano giá all'interno del progetto (local per quella in locale, quella senza é quella in remoto) 
e **NON** si deve avviare **Glassfish** per eseguire la webapp in quanto se ne occuperá Intellij.

Per la configurazione in remoto é necessario connettersi alla vpn, mentre per quella in locale no. La configurazione locale puó essere utile per il debugging
in quanto é possibile stampare attraverso *System.err.println(qualche_string)* messaggi che appariranno sul **log di Glassfish** in Intellij.

La configurazione in remoto si occupa di trasferire i file compilati (la cartella *out*) dalla macchina locale al container *172.18.0.24* attraverso SFTP. 
Potrebbe quindi essere necessario configurare una connessione ssh tramite Intellij se non viene creata automaticamente dalla configurazione. 
Occorre togliere la spunta dall'opzione:
[x] Upload with Glassfish 
nella configurazione e comparirá un menú per la scelta del protocollo da usare e i path da configurare (dovrebbe essere tutto giá fatto).

Dopo aver cliccato sul pulsante della run, Intellij si occuperá di eseguire la build e il deployment in maniera automatica e non si dovrá fare altro che aspettare
l'apertura del browser predefinito con la webapp.

## Troubleshooting

Errori abbastanza tipici facilmente risolvibili:

- Bean identifier index inconsistency detected - the distributed container probably does not work with identical applications:
    Errore che si puó verificare dopo la modifica dei file relativi ai *bean*, risolvibile andando a cancellare la cartella WeSport_webapp sulla macchina virtuale 
    *172.18.0.24* tramite:
    `rm -rf WeSport_webapp`
    (Mi raccomando da usare **SOLO E SOLTANTO** sulla VM perché altrimenti si cancella tutta la webapp)
- 404 not found: 
    Tipicamente si é sbagliato un indirizzo da qualche parte o ancora manca la pagina.
- 500 internal state error:
    Tipicamente causato da un'eccezione che viene anche stampata sulla pagina dell'errore. Per vedere di preciso da cosa é causata si possono controllare i log del server.
- Connection refused: 
    Si sta cercando di far partire l'applicazione con il server giá attivo. Bisogna stoppare il server sulla VM o in locale e basta fare:
    `cd glassfish5/glassfish/bin`
    `./asadmin stop-domain domain1`
- SSH connection refused:
    Si sta cercando di avviare l'applicazione in remoto senza aver avviato la vpn.
