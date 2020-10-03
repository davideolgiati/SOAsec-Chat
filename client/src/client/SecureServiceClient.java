package client;

import java.util.*;

public class SecureServiceClient {
  protected static String username = "";
  protected static String password = "";
  private static Thread listener;
  private static Boolean stop = false;
  private static Boolean stopped = false;
  private static String newUser = "";

  private static Boolean getStopped(){
	return stopped;
  }

  private static Boolean getStop(){
	return stop;
  }

  private static void setStopped(Boolean val) {
	stopped = val;
  }

  private static void setNewUser(String val) {
	newUser = val;
  }

  private static void startListener(final ChatAPI chat) {
    // Lambda Runnable
    Runnable msgPrinter =
	() -> {
	  String msg = "";
	  String peer = chat.getPeer();
	  while (true) {
	    msg = chat.ricevi();
	    if (!("".equals(msg) || msg == null)) {
	      stop = true;
	      System.out.println(peer + " : " + msg);
	    }
	  }
	};
    // start the thread
    listener = new Thread(msgPrinter);
    listener.start();
  }

  public static void main(String[] args) throws Exception {
    // Input
    Scanner jkeyboard = new Scanner(System.in);

    // Username
    System.out.println("Inserisci il tuo nome utente:");
    username = jkeyboard.next();

    // Password
    System.out.println("Inserisci la tua password:");
    password = jkeyboard.next();

    Keyboard keyboard = new Keyboard();
    ChatAPI chat = new ChatAPI(username);
    String users = chat.listaUtenti();
    String peer = "";

    Keyboard.Cond userStop =
	() -> {
	  setStopped(false);
	  String[] tmp = chat.ricevi().split(" ");
	  if (tmp[0] == "<open>") {
	    setStopped(true);
	    setNewUser(tmp[1]);
	  }
	  return getStopped();
	};

    Keyboard.Cond msgStop =
	() -> {
	  return getStop();
	};

    while (!stopped) {
      System.out.println("Con quale utente vuoi chattare?");
      System.out.println(stopped);
      peer = keyboard.next(userStop);
      if (stopped) {
	System.out.println("L'utente " + newUser + " vuole chattare con te!");
	System.out.println("Accettare l'invito? [S/n]");
	char ask = keyboard.next().toLowerCase().charAt(0);
	if (ask == 's') {
	  chat.invia("<open> " + newUser, newUser);
	} else {
	  System.out.println("Scarto richiesta da " + newUser + " ...");
	  chat.invia("<close> " + newUser, newUser);
	  stopped = false;
	}
      } else {
	if (!users.contains(peer)) {
	  System.out.println("L'utente " + peer + " non esiste!");
	  System.out.println("Inserire un utente valido!");
	  stopped = false;
	  peer = "";
	} else {
	  chat.connectTo(peer);
	  stopped = true;
       }
      }
    }

    startListener(chat);
    String msg = "";
    while (!":quit".equals(msg)) {
      stop = false;
      msg = keyboard.next(msgStop);
      if (stop) {
	msg = "";
      }
      if (!("".equals(msg) || ":quit".equals(msg))) {
	chat.invia(msg);
      }
    }
    // to be continued
  }
}
