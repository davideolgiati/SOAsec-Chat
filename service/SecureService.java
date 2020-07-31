import java.io.*;
import chatUtils.Parser;
import chatUtils.Data;

public class SecureService {

    private Parser parser = new Parser();
    private Data data = new Data();
    
    //Login method
    public void chatLogin(String user) {
    	data.create(user);
    }

    // API per l'invio di un messaggio
    // Gli argomenti di questo metodo sono di Stringhe, una contenente
    // il messaggio da inviare e una contenente l'utente a cui inviare
    // il messaggio
    public String sendMsg(String message, String user) {
	try {
	    return parser.parse(message, user);
	} catch (Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
	}
    }

    public String reciveMsg(String user) {
	try {
	    // Aggiorno il contenuto della coda e lo faccio chiamndo
	    // il metodo QueuePull definito in precedenza.
	    return parser.getFor(user);
	} catch (Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
	}
    }
}
