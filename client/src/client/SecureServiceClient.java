package client;

public class SecureServiceClient {
    protected static String username = "";
    protected static String password = "";

    private Thread listener;

    private void startListener(final ChatAPI chat) {
	// Lambda Runnable
	Runnable msgPrinter = () -> {
		String msg = "";
		String peer = chat.getPeer();
		while (true) {
		    msg = chat.ricevi();
		    if (!("".equals(msg) || msg == null)) {
			System.out.println(peer + " : " + msg);
		    }
		}
	};
	// start the thread
	listener = new Thread(task2(chat));
	listener.start();
    }

    public static void main(String[] args) throws Exception {
	// Input
	Scanner keyboard = new Scanner(System.in);

	// Username
	System.out.println("Inserisci il tuo nome utente:");
	username = keyboard.next();

	// Password
	System.out.println("Inserisci la tua password:");
	password = keyboard.next();

	ChatAPI chat = new ChatAPI(username);
	String users = chat.listaUtenti();

	System.out.println("Con quale utente vuoi chattare?");
	String peer = "";
	peer = keyboard.next();

	while(!users.contains(peer)){
	    System.out.println("L'utente " + peer + " non esiste!");
	    System.out.println("Inserire un utente valido!");
	    System.out.println("Con quale utente vuoi chattare?");
	    peer = keyboard.next();
	}

	if(chat.connectTo(peer)) {
	    startListener();
	    String msg = "";
	    while (!":quit".equals(msg)){
		msg = keyboard.next();
		if (!("".equals(msg) || ":quit".equals(msg))){
		    chat.invia(msg);
		}
	    }
	    // to be continued
	} else {
	    System.out.println("Oh no, si è rotto tutto");
	    System.out.println("... eh va beh");
	    System.out.println("Ciaoooo");
	}
    }
}
