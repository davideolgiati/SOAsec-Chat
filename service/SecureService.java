import java.io.IOException;
import java.util.List;

/*
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

	// Eccezioni

	/*
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

		public UserNotFoundException(String utente) {
			// Il messaggio si limita al nome utente, scelta di stile e funzinale
			// abbiamo osservato che in caso di stampa del messaggio l'errore
			// risulta più chiaro
			super(utente);
		}
	}

	// Variabili

	private States states;
	private Requests requests;
	private Messages messages;

	public SecureService() throws IOException {
		states = new States();
		requests = new Requests();
		messages = new Messages();
	}

	// Metodi Wrapper

	// privi di commenti in quanto non aggiungono nulla alla computazione rispetto a
	// quanto succede nei loro
	// metodi "principali"

	public void sendMsg(String message, String user) throws ClassNotFoundException, IOException {
		messages._put(user, message);
	}

	public String reciveMsg(String user) throws ClassNotFoundException, IOException {
		return messages._get(user);
	}

	public String userList(String user) throws NullPointerException, ClassNotFoundException, IOException {
		return messages.listUsers(user);
	}

	public boolean status(String me, String myFriend) throws Exception {
		return requests.status(me, myFriend);
	}

	public boolean accept(String me, String myFriend) throws ClassNotFoundException, IOException {
		return requests.accept(me, myFriend);
	}

	public void doLogin(String user) throws ClassNotFoundException, IOException {
		messages.addUser(user);
		states.ready(user);
	}

	public void doLogout(String user) throws ClassNotFoundException, IOException {
		messages.deleteUser(user);
		states.remove(user);
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
