import java.io.IOException;
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
	/* Eccezioni
	 *
	 * UserNotFoundException è un eccezione personalizzata utile, lato client, per
	 * rilevare errori riguardo la presenza (assenza) dell'utente desiderato sul
	 * server
	 * 
	 * La struttura dell'eccezione è standard. La creazione di un eccezione a se per
	 * questo tipo di errore è stata una scelta dettata dal desiderio di avere più
	 * chiarezza nella gestione degli errori
	 */
	public class UserNotFoundException extends Exception {
		private static final long serialVersionUID = 1L;

		public UserNotFoundException(String user) {
			// Il messaggio si limita al nome utente, scelta di stile e funzinale
			// abbiamo osservato che in caso di stampa del messaggio l'errore
			// risulta più chiaro
			super(user);
		}
	}

	/* Variabili
	 * 
	 * Le variabili della classe sono 3 e sono tutte dichiarate con modifier private.
	 * Queste tre classi rappresentano gli ingranaggi che permettono al server di funzionare 
	 * bene e in modo chiaro.
	 * La scelta di dividere la logica in tre classi distinte deriva dalla volontà di separare
	 * diversi ambiti di competenza.
	 * 
	 * Rimando alle classi in sè la spiegazione del loro ambito e del loro funzionamento:
	 * 
	 * States:   service/Statse.java
	 * Requests: service/Requests.java
	 * Messages: service/Messages.java
	 */
	private States states;
	private Requests requests;
	private Messages messages;

	/* Costruttore
	 *
	 * Il costruttore è molto banale, vengono inizializzate le tre classi viste sopra.
	 * La particolarità di questo metodo è che se avviene un errore in inizializzatione
	 * in una delle tre classi (IOException) questo non viene gestito ma demandato al client.
	 */
	public SecureService() throws IOException {
		states = new States();
		requests = new Requests();
		messages = new Messages();
	}

	/* Metodi Wrapper
	 *
	 * privi di commenti in quanto non aggiungono nulla alla computazione rispetto a
	 * quanto succede nei loro
	 * metodi "principali"
     */
	public void sendMsg(String message, String user) throws ClassNotFoundException, IOException,
			SecureService.UserNotFoundException {
		if (!messages.userExists(user)) {
			throw new UserNotFoundException(user);
		} else {
			messages._put(user, message);
		}
	}

	public String reciveMsg(String user) throws ClassNotFoundException, IOException,
			SecureService.UserNotFoundException {
		if (!messages.userExists(user)) {
			throw new UserNotFoundException(user);
		} else {
		return messages._get(user);
		}
	}

	public String userList(String user) throws NullPointerException, ClassNotFoundException, IOException {
		return messages.listUsers(user);
	}

	public boolean status(String me, String myFriend) throws Exception {
				if (!messages.userExists(me)) {
			throw new UserNotFoundException(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFoundException(myFriend);
		} else {
		return requests.status(me, myFriend);
		}
	}

	public boolean accept(String me, String myFriend) throws ClassNotFoundException, IOException {
				if (!messages.userExists(me)) {
			throw new UserNotFoundException(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFoundException(myFriend);
		} else {
		return requests.accept(me, myFriend);
		}
	}

	public void doLogin(String user) throws ClassNotFoundException, IOException {
				if (!messages.userExists(me)) {
			throw new UserNotFoundException(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFoundException(myFriend);
		} else {
		messages.addUser(user);
		states.ready(user);
		}
	}

	public void doLogout(String user) throws ClassNotFoundException, IOException {
				if (!messages.userExists(me)) {
			throw new UserNotFoundException(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFoundException(myFriend);
		} else {
		messages.deleteUser(user);
		states.remove(user);
		}
	}

	public boolean deny(String me, String myFriend) throws ClassNotFoundException, IOException {
		states.ready(me);
		states.ready(myFriend);
		return requests.deny(me, myFriend);
	}

	public String[] checkForRequests(String me) throws ClassNotFoundException, IOException {
		List<String> req = requests.checkForRequests(me);
		return req.toArray(new String[req.size()]);
	}

	public void request(String me, String myFriend)
			throws ClassNotFoundException, IOException, SecureService.UserNotFoundException, Exception {
		if (!messages.userExists(me)) {
			throw new UserNotFoundException(me);
		} else if (!messages.userExists(myFriend)) {
			throw new UserNotFoundException(myFriend);
		} else {
			if (states.isReady(myFriend)) {
				requests.request(me, myFriend);
				states.busy(me);
				states.busy(myFriend);
			} else {
				throw new Exception("utente occupato");
			}
		}
	}

}
