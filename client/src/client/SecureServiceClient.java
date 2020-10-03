package client;

import java.util.*;

public class SecureServiceClient {
  protected static String username = "";
  protected static String password = "";
  private static Thread listener;
  private static Boolean stop = false;

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
    Keyboard keyboard = new Keyboad();

    // Username
    System.out.println("Inserisci il tuo nome utente:");
    username = keyboard.next();

    // Password
    System.out.println("Inserisci la tua password:");
    password = keyboard.next();

    ChatAPI chat = new ChatAPI(username);
    String users = chat.listaUtenti();
    Boolean stopped = false;
    String newUser = "";
    String peer = "";

    Keyboard.Cond userStop =
	() -> {
	  String tmp = chat.ricevi().split(" ");
	  if (tmp[0] == "<open>") {
	    stopped = true;
	    newUser = tmp[1];
	  }
	  return stopped;
	};

    Keyboard.Cond msgStop =
	() -> {
	  return stop;
	};
    while (!users.contains(peer) && !stopped) {
      System.out.println("Con quale utente vuoi chattare?");
      peer = keyboard.next(userStop);
      if (stopped) {
	System.out.println("L'utente " + newUser + " vuole chattare con te!");
	System.out.println("Accettare l'invito? [S/n]");
	String ask = keyboard.next().toLowerCase.charAt(0);
	if (ask == 's') {
	  chat.invia("<open> " + newUser, newUser);
	} else {
	  System.out.println("Scarto richiesta da " + newUser + " ...");
	  chat.invia("<close> " + newUser, newUser);
	  stopped = false;
	}
      } else {
	if (!user.contains(peer)) {
	  System.out.println("L'utente " + peer + " non esiste!");
	  System.out.println("Inserire un utente valido!");
	} else {
	  chat.connectTo(peer);
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
