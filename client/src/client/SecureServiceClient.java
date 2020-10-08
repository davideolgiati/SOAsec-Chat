package client;

import java.util.*;

public class SecureServiceClient {
  // VARIABILI GLOBALI
  // username per axis2
  protected static String username = "";
  // password axis2
  protected static String password = "";

  // thread la ricezione dei nuovi messaggi
  private static Thread listener;

  // funzione per l'avvio del thread sopra
  private static void startListener(final ChatAPI chat) {
    // Funzione Labda per la lettura continua dei messaggi
    Runnable msgPrinter =
    	() -> {
        // messaggio letto
        String msg = "";
        // utente con cui chattiamo
        String peer = chat.getPeer();
        // ciclo infinito per la lettura dei messaggi
	      while (true) {
          // ricezione del messaggio
          msg = chat.ricevi();
          // se il messaggio non è vuoto o nullo lo stampo
	        if (!("".equals(msg) || msg == null)) {
	          System.out.println(peer + " : " + msg);
	        }
	      }
      };
    // assegno la lambda al thread
    listener = new Thread(msgPrinter);
    // e lo avvio
    listener.start();
  }

  public static void main(String[] args) throws Exception {
    // Tastiera
    Scanner keyboard = new Scanner(System.in);

    // Lettura username
    System.out.println("Inserisci il tuo nome utente:");
    username = keyboard.next();

    // Lettura password
    System.out.println("Inserisci la tua password:");
    password = keyboard.next();

    ChatAPI chat = new ChatAPI(username);
    String peer = "";

    System.out.println("Con quale utente vuoi chattare?");
    peer = keyboard.next();

    chat.connectTo(peer);

    startListener(chat);
    String msg = "";
    while (!":quit".equals(msg)) {
      msg = keyboard.next();
      if (!("".equals(msg) || ":quit".equals(msg))) {
	      chat.invia(msg);
      }
    }
    // to be continued
    keyboard.close();
  }
}
