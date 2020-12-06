# Semplice chat scritta in Java e implementata secondo il protocollo SOAP.

### Funzionamento:

All'avvio del programma viene richiesto all'utente di autenticarsi, le credenziali valide disponibili sono "client1" e "client2" rispettivamente con password "password1" e "password2". Per semplicità non è possibile creare nuovi utenti.
Una volta che l'utente ha eseguito il login, il programma stampa a video un banner contenente i principali comandi che possono essere eseguiti; digitando ":c" vengono listati gli altri utenti online e viene chiesto con quale di essi si vuole iniziare una conversazione. Per terminare poi la conversazione è possibile digitare ":e" (il client vittima di tale azione si accorgerà dell'accaduto solo quando proverà ad inviare un messaggio).

##### Politiche di sicurezza

Tutti i metodi evocati durante l'utilizzo della chat utilizzano le features di WS-Security di UsernameToken Authentication, Signature ed Encryption. Inoltre tramite l'utilizzo di WS-Policy si richiede obbligatoriamente il protocollo HTTPS.
