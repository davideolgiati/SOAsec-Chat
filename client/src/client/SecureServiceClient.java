package client;

import java.util.*;

public class SecureServiceClient {
  protected static String username = "";
  protected static String password = "";
  private static Thread listener;
  private static Boolean stop = false;

  private static Keyboard.Cond = () -> {
    return stop;
  };

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
    Keyboard keyboard = new Keyboad(System.in);

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

    while (!users.contains(peer)) {
      System.out.println("L'utente " + peer + " non esiste!");
      System.out.println("Inserire un utente valido!");
      System.out.println("Con quale utente vuoi chattare?");
      peer = keyboard.next();
    }

    if (chat.connectTo(peer)) {
      startListener(chat);
      String msg = "";
      while (!":quit".equals(msg)) {
	stop = false;
	msg = keyboard.next();
	if (stop) {
	  msg = "";
	}
	if (!("".equals(msg) || ":quit".equals(msg))) {
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
