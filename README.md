# Semplice chat scritta in Java e implementata secondo il protocollo SOAP.

### Funzionamento:

Un client che vuole utilizzare la chat deve effettuare il login per poter usufruire del servizio. Ciò è implementato attraverso un metodo apposito a cui si applicano protocolli di simple authentication (con cui effettivamente avviene il login) e timestamp (per evitare replay attack).
Per semplicità non è possibile registrarsi, il server conosce già quali sono gli utenti autorizzati.

Una volta che l'utente è loggato, esso viene contrassegnato come online e può visualizzare gli altri utenti online, in modo da poterne scegliere uno ed iniziare una conversazione.
La conversazione tra due utenti si basa sull'utilizzo di encryption e signature.

##### WS-POLICY

Una possibile implementazione di ws-policy in questo scenario riguarderebbe la ripartizione temporale delle azioni attuabili dall'utente. Un esempio molto banale potrebbe essere la scelta della chiave di seesione per il canale tra i due client. In questo scenario avremo un client "master", che prende le decisioni,  e uno "slave", che le subisce. Per semplicità diamo a chi comincia la chat il ruolo di master. il client master potrà scegliere e comunicare la chiave di sessione mentre lo slave potrà solo recepirla.
Un secondo scenario possibile è relativo alla chiusura della chat. In caso che la chat venga chiusa da una delle due parti, la parte ancora attiva sarà impossibilitata a compiere qualsiasi azione che non sia richiedere l'ultimo messaggio disponibile (una volta richiesto il quale l'istanza della canale tra i due attori sul server sarà cancellata)
