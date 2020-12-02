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
  private static int millis;
  private static boolean close = false;
  private static boolean keep = true;
  // inizializzo la tastiera
  private static Scanner keyboard = new Scanner(System.in);

  // funzione per l'avvio del thread sopra
  private static void startListener(final ChatAPI chat) {
    // Funzione Labda per la lettura continua dei messaggi
    Runnable msgPrinter = () -> {
      // messaggio letto
      String msg = "";
      // utente con cui chattiamo
      String peer = chat.getPeer();
      // ciclo infinito per la lettura dei messaggi
      while (!close) {
        // ricezione del messaggio
        try {
          msg = chat.ricevi();
        } catch (Exception e) {
          msg = "";
        }
        // se il messaggio non è vuoto o nullo lo stampo
        if (!("".equals(msg) || msg == null)) {
          if (":e".equals(msg)) {
            keep = false;
            keyboard.close();
            keyboard = null;
            System.out.println("\n" + peer + " ha chiuso la conversazione\n");
          } else {
            System.out.println(peer + " : " + msg);
          }
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    };
    // assegno la lambda al thread
    listener = new Thread(msgPrinter);
    // e lo avvio
    listener.start();
  }

  // funzione help
  private static String help() {
    // compongo il banner
    String text = "\n\nBenvenuto in SOAsec-Chat!\n";
    text += "Eccoti le azioni possibili :\n";
    text += "(digitare il codice corrisponente all'azione)\n\n";
    text += "CODICE\tAZIONE\n";
    text += ":h\tmostra questo banner\n";
    text += ":c\tinizia a chattare\n";
    text += ":e\tesci (valido anche durante la chat)\n\n>\t";

    System.out.print(text);

    // leggo la risposta
    String res = keyboard.next();
    // ritorno la risposta
    return res;
  }

  // funzione di decodifica comando utente
  private static int decode(String res) {
    int ret = 0;
    switch (res) {
      case "": // Stringa vuota
        ret = 0;
        break;
      case ":c": // Comando per cominciare la chat
        ret = 1;
        break;
      case ":h": // Comando per mostrare l'help
        ret = 2;
        break;
      case ":e": // Comando per uscire dal programma
        ret = 3;
        break;
      default: // Stringa non riconosciuta
        ret = -1;
        break;
    }
    return ret;
  }

  public static void main(String[] args) throws Exception {

    // Lettura username
    System.out.println("Inserisci il tuo nome utente:");
    username = keyboard.next();

    // Lettura password
    System.out.println("Inserisci la tua password:");
    password = keyboard.next();

    ChatAPI chat = new ChatAPI(username);
    String peer = "";
    String res = "";
    int parse = 0;

    while (parse < 1) {
      res = help();
      parse = decode(res);
      if (parse == 2) {
        parse = 0;
      }
    }

    if (parse == 1) {
      String lista = "";

      do {
        lista = chat.listaUtenti();
        System.out.println(lista);
        if ("Ancora nessun utente connesso ...".equals(lista)) {
          System.out.println("Retry in 2s ...");
          millis = 2 * 1000;
          Thread.sleep(millis);
        }
      } while ("Ancora nessun utente connesso ...".equals(lista));

      Boolean firstTime = true;

      do {
        if (firstTime) {
          System.out.println("Con quale utente vuoi chattare?");
        } else {
          System.out.println("Inserisci un utente tra questi, per favore:\n\n" + lista + "\n");
        }
        peer = keyboard.next();
      } while (!lista.contains(peer) || "".equals(peer) || username.equals(peer));

      if (chat.connectTo(peer)) {
        startListener(chat);

        String msg = "";
        while (keep) {
          msg = keyboard.nextLine();

          if (!("".equals(msg))) { 
            chat.invia(msg);
            if (":e".equals(msg)) {
              keep = false;
            }
          }
        }
        close = true;
        System.out.println("System : Ciao ciao!");
      }
    }
    if (keyboard != null)
      keyboard.close();
  }
}