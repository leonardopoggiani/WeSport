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
in quanto é possibile stampare attraverso *System.err.println(qualche_string)* messaggi che appaiono sul **log di Glassfish**.

La configurazione in remoto si occupa di trasferire i file compilati (la cartella *out*) dalla macchina locale al container *172.18.0.24* attraverso SFTP. 
Potrebbe quindi essere necessario configurare una connessione ssh tramite Intellij se non viene creata automaticamente dalla configurazione. 
Occorre togliere la spunta dall'opzione:
[x] Upload with Glassfish 
nella configurazione e comparirá un menú per la scelta del protocollo da usare e i path da configurare (dovrebbe essere tutto giá fatto).

### Configurazione in locale

Per la configurazione in locale é necessario creare il database e il *connection pool* di **Glassfish**, cosa che invece sulla virtual machine *172.18.0.24* é giá fatta. Per creare il db per prima cosa é necessario creare un nuovo utente e garantirgli tutti i privilegi. \\

`mysql -u root -p` 
e inserire la password *root*.
`CREATE USER 'wesport'@'localhost' IDENTIFIED BY 'wesport';`
`GRANT PRIVILEGE ON *.* TO 'wesport'@'localhost';`

A questo punto occorre uscire ed entrare con il nuovo utente creato importando il dump del database sql.
`mysql -u wesport -p wesport < WeSport_project.sql`

Una volta importato il db occorre configurare **Glassfish**.
Per prima cosa avviare il server:
`cd glassfish5/glassfish/bin`
`./asadmin start-domain domain1`

Connettersi alla admin console all'indirizzo `http://localhost:4848`.
É necessario creare un *connection pool* (istruzioni presenti anche nel file *Guidelines_etc..* del professore, basta mettere i valori che abbiamo noi):
- DatabaseName: wesport
- User: wesport
- Password: wesport
- URL e URI: jdbc:mysql://localhost:3306/wesport
- useSSL: prima va messo a true e poi riportato a false dopo aver salvato
- AllowPublicKeyRetrieval: true
- serverName: localhost
- Datasource Classname: **ATTENZIONE** va settato inizialmente a `com.mysql.cj.jdbc.MysqlDataSource` ma potrebbe in seguito dare un errore quando si risetta useSSL a false. In caso dia un errore del tipo *Classpath not found/recognized* va rimesso a `com.mysql.jdbc.jdbc2.optional.MysqlDataSource`.

Bisogna inoltre ricordarsi di copiare il file del *lab_09_resources* (`mysql-connector-java-8.0.18.jar`) nella cartella `glassfish5/glassfish/domains/domain/lib/ext`.
A questo punto si puó provare a pingare il database, se il ping va a buon fine (*Ping succeded*) si puó passare a creare una risorsa su cui viene esposto il JDBC connection pool (quello che si accede attraverso Java). É sufficiente andare su *Resource* sempre dalla admin console di **Glassfish** e creare una nuova risorsa impostando come nome `jdbc/wesport_pool` e come Pool quello che abbiamo appena creato.

Dopo aver cliccato sul pulsante della run, Intellij si occuperá di eseguire la build e il deployment in maniera automatica e non si dovrá fare altro che aspettare
l'apertura del browser predefinito con la webapp.

### Troubleshooting

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