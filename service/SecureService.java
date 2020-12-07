import java.util.List;

/* SecureService
 *
 * Classe principale contenetnte tutte le API utilizzate lato client e la logica di
 * coordinamento tra le diverse classi operative (messages, states, requests) 
 * 
 * I metodi in questa classe sono essenzialmente dei wrapper per le classi che 
 * gestiscono le diverse logiche di funzionamento del server. La maggior parte di 
 * essi ritornano i risultati dei metodi per cui fanno da tramite senza modificare 
 * nulla. Altri uniscono diversi metodi di diverse classi in modo da rendere 
 * consistente lo stato del server a seconda delle richieste.
 * In fine il metodo
 * 
 * public void request(String me, String myFriend)
 * 
 * introduce del codice aggiuntivo oltre a fare da tramite. Maggiori dettagli sul
 * metodo forniti in seguito
 * 
 * Un'altra scelta stilistica interessante è stata quella di rimandare al client la 
 * gestione delle eccezioni. Ci si è orientati in questa direzione per due motivi
 * principali:
 * 
 *  - maggiore chiarezza per il client riguardo la computazione remota
 *  - esigenze di debug (motivazione debole)
 */
public class SecureService {
	/*
	 * Variabili
	 * 
	 * Le variabili della classe sono 3 e sono tutte dichiarate con modifier
	 * private. Queste tre classi rappresentano gli ingranaggi che permettono al
	 * server di funzionare bene e in modo chiaro. La scelta di dividere la logica
	 * in tre classi distinte deriva dalla volontà di separare diversi ambiti di
	 * competenza.
	 * 
	 * Rimando alle classi in sè la spiegazione del loro ambito e del loro
	 * funzionamento:
	 * 
	 * States: service/Statse.java Requests: service/Requests.java Messages:
	 * service/Messages.java
	 */
	private States states;
	private Requests requests;
	private Messages messages;

	/*
	 * Costruttore
	 *
	 * Il costruttore è molto banale, vengono inizializzate le tre classi viste
	 * sopra. La particolarità di questo metodo è che se avviene un errore in
	 * inizializzatione in una delle tre classi (IOException) questo non viene
	 * gestito ma demandato al client.
	 */
	public SecureService() throws StorageError {
		states = new States();
		requests = new Requests();
		messages = new Messages();
	}

	/*
	 * Metodi Wrapper
	 *
	 * privi di commenti in quanto non aggiungono nulla alla computazione rispetto a
	 * quanto succede nei loro metodi "principali"
	 */
	public void sendMsg(String message, String user)
			throws UserNotFound, StorageError {
		if (!messages.userExists(user)) {
			throw new UserNotFound(user);
		} else {
			messages.put(user, message);
		}
	}

	public String reciveMsg(String user)
			throws UserNotFound, StorageError {
		if (!messages.userExists(user)) {
			throw new UserNotFound(user);
		} else {
			return messages.get(user);
		}
	}

	public String userList(String user) throws StorageError {
		return messages.listUsers(user);
	}

	public boolean status(String me, String myFriend)
			throws StorageError, UserNotFound, NoResponse {
		if (!messages.userExists(me)) {
			throw new UserNotFound(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFound(myFriend);
		} else {
			return requests.status(me, myFriend);
		}
	}

	public boolean accept(String me, String myFriend)
			throws UserNotFound, StorageError {
		if (!messages.userExists(me)) {
			throw new UserNotFound(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFound(myFriend);
		} else {
			return requests.accept(me, myFriend);
		}
	}

	public void doLogin(String user) throws StorageError {
		messages.addUser(user);
		states.ready(user);
	}

	public void doLogout(String user) throws UserNotFound, StorageError {
		if (!messages.userExists(user)) {
			throw new UserNotFound(user);
		} else {
			messages.deleteUser(user);
			states.remove(user);
		}
	}

	public boolean deny(String me, String myFriend)
			throws UserNotFound, StorageError {
		if (!messages.userExists(me)) {
			throw new UserNotFound(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFound(myFriend);
		} else {
			states.ready(me);
			states.ready(myFriend);
			return requests.deny(me, myFriend);
		}
	}

	public String[] checkForRequests(String me)
			throws UserNotFound, StorageError {
		if (!messages.userExists(me)) {
			throw new UserNotFound(me);
		} else {
			List<String> req = requests.checkForRequests(me);
			return req.toArray(new String[req.size()]);
		}
	}

	public void request(String me, String myFriend)
			throws UserNotFound, UserBusy, StorageError {
		if (!messages.userExists(me)) {
			throw new UserNotFound(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFound(myFriend);
		} else {
			if (states.isReady(myFriend)) {
				requests.request(me, myFriend);
				states.busy(me);
				states.busy(myFriend);
			} else {
				throw new UserBusy(myFriend);
			}
		}
	}

}