# Semplice chat scritta in Java e implementata secondo il protocollo SOAP.

### Funzionamento:

Un client che vuole utilizzare la chat deve effettuare il login per poter usufruire del servizio. Ciò è implementato attraverso un metodo apposito a cui si applicano protocolli di simple authentication (con cui effettivamente avviene il login) e timestamp (per evitare replay attack).
Per semplicità non è possibile registrarsi, il server conosce già quali sono gli utenti autorizzati.

Una volta che l'utente è loggato, esso viene contrassegnato come online e può visualizzare gli altri utenti online, in modo da poterne scegliere uno ed iniziare una conversazione.
La conversazione tra due utenti si basa sull'utilizzo di encryption e signature.

##### SCRIVERE QUI UTILIZZO DI WS-POLICY
