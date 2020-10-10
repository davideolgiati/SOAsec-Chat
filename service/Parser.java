package chatUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// La classe deve essere serializzabile, quindi aggiungiamo
// "implement Serializable" per renderla tale
public class Parser implements Serializable {
  // questa variabile serve per la classe Serializable
  private static final long serialVersionUID = 22072020L;
  // data conterrà le informazione relative alle chat
  private Data data = null;
  // questo è il percorso dove serializeremo la chat
  private String tmpfile = System.getProperty("java.io.tmpdir") + "/usersdata.ser";
  // questa struttura registra i match, forse è inutile?
  // non lo so, sto solo pensando ad alta voce, da discutere
  private Map<String, Boolean> match = new HashMap<>();

  // quest'interfaccia esiste per rendere possibie l'indicizzazione dei comandi 
  // in una struttura dati dedicata:
  // Interfaccia -> Classe -> Comando
  // KeyWord : (Classe -> Comando)
  private interface Command {
    public void execute(String[] user);
  }

  // I comandi sono confusionari e poco chiari, VANNO RISTRUTTURATI

  // comando per chiudere la conversazione
  private class CloseConversation implements Command {
    public void execute(String[] user) {
      String msg = "<close> " + user[0];
      parse(msg, user[1]);
      match.keySet().removeIf(key -> key == user[0] || key == user[1]);
    }
  }

  // comando per sconnettersi
  private class ChatLogout implements Command {
    public void execute(String[] user) {
      data.delete(user[0]);
    }
  }

  // comando per listare gli utenti
  private class ListUsers implements Command {
    public void execute(String[] user) {
      parse(data.usersToString(""), user[0]);
    }
  }

  // comando per aprire la conversazione
  private class OpenConversation implements Command {
    public void execute(String[] user) {
      if (!(match.containsKey(user[0]) || match.containsKey(user[1]))) {
        String msg = "<request> " + user[0];
        parse(msg, user[1]);
      } else {
        throw new IllegalArgumentException("user busy");
      }
    }
  }

  private Map<String, Command> cmds = new HashMap<>();

  public Parser() {
    try {
      File file = new File(tmpfile);
      if (file.exists() && file.isFile()) {
        FileInputStream fileIn = new FileInputStream(tmpfile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        data = (Data) in.readObject();
        in.close();
        fileIn.close();
      } else {
        file.createNewFile();
        data = new Data();
      }
    } catch (Exception e) {
      data = new Data();
    }

    cmds.put(":listUsers", new ListUsers());
    cmds.put(":openConversation", new OpenConversation());
    cmds.put(":closeConversation", new CloseConversation());
    cmds.put(":chatLogout", new ChatLogout());
  }

  private void dump() {
    try {
      FileOutputStream fileOut = new FileOutputStream(tmpfile);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(data);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  public String parse(String message, String user) {
    // variabile contenente tutte le parole del messaggio
    String[] stringList = new String[0];
    String result = "";
    System.out.println("[INFO] inizio metodo chatUtils.Parser.parse");
    if (message == null || message.equals("")) {
      System.out.println("[INFO] rilevata stringa vuota");
      // questo e' il caso in cui l'utente ha premuto invio
      // senza aver scritto niente e quindi il programma
      // non dovra' fare niente
    } else {
      stringList = message.split(" ");
      if (message.charAt(0) != ':') {
        System.out.println("[INFO] messaggio non contenete comandi");
        // se il messaggio non contiene un comando
        // allora...
        data.push(user, message);
      } else {
        System.out.println("[INFO] possibile comando rilevato");
        // esecuzione del comando
        String command = stringList[0]; // .toLowerCase();
        if (cmds.containsKey(command)) {
          System.out.println("[INFO] Comando " + command + " riconosciuto");
          Command cmd = cmds.get(command);
          System.out.println("[INFO] Inizio esecuzione comando");
          String[] users;
          if (stringList.length > 1) {
            users = new String[] { user, stringList[1] };
          } else {
            users = new String[] { user };
          }
          cmd.execute(users);
        }
      }
    }
    dump();
    return result;
  }

  public String getFor(String user) {
    String res = data.poll(user);
    dump();
    return res;
  }

  public void pLogin(String user) {
    data.create(user);
    dump();
  }
}
