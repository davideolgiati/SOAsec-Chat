import java.io.*;
import java.util.List;

public class SecureService {

	// Eccezioni

	public class UserNotFoundException extends Exception {
		private static final long serialVersionUID = 1L;
		private String details = "";

		private void composeMessage(String utente) {
			details = "utente \"" + utente + "\" non trovato";
		}

		public String getDetails() {
			return details;
		}

		public UserNotFoundException(String utente) {
			super(utente);
			composeMessage(utente);
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

	public void sendMsg(String message, String user) throws ClassNotFoundException, IOException {
		messages._put(user, message);
	}

	public String reciveMsg(String user) throws ClassNotFoundException, IOException {
		return messages._get(user);
	}

	public void doLogin(String user) throws ClassNotFoundException, IOException {
		messages.addUser(user);
		states.ready(user);
	}

	public void doLogout(String user) throws ClassNotFoundException, IOException {
		messages.deleteUser(user);
		states.remove(user);
	}

	public String userList(String user) throws NullPointerException, ClassNotFoundException, IOException {
		return messages.listUsers(user);
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

	public boolean accept(String me, String myFriend) throws ClassNotFoundException, IOException {
		return requests.accept(me, myFriend);
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

	public boolean status(String me, String myFriend) throws Exception {
		return requests.status(me, myFriend);
	}

}
