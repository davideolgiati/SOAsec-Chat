import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SecureService {
  private class UsersDataStorage implements Serializable {
    private static final long serialVersionUID = 21072020L;
    // Definisco la struttura principale per la gestione dei messaggi
    // Scelgo di utilizzare un ArrayList di Queue.
    // Utilizzo ArrayList perchè voglio una struttura che mi tenga
    // traccia delle code di messagio per ogni utente. In assenza dei
    // puntatori ArrayList e LinkedList dono le uniche due scelte
    // possibili. Scelgo ArrayList per la possibilità di accedere
    // tramite offset.
    // Un array normale non sarebbe bastato perchè la dimensione deve
    // essere dinamica

    // L'utilizzo di Queue è abbastanza autoesplicativo. Voglio che
    // ogni utente possa leggere i messaggi secondo l'ordine d'invio

    private Map<String, Queue<String>> code = new HashMap<String, Queue<String>>();

    // Per aggiornare lo stato delle code (sia push che pop) dobbiamo
    // salvare le code originali in una variabile temporanea, aggiornare
    // e poi riscrivere i valori finali

    // STEP

    // 1. LEGGO LA CODA
    // 2. MODIFICO
    // 3. SE LA MODIFICA E' COMPLETA VAI A 4. ALTRIMENTI TORNA 2.
    // 4. SCRIVO IL VALORE MODIFICATO NELLA POSIZIONE ORIGINALE

    // Metodo privato per l'inserimento di un valore nella coda
    // all'offset specificato
    public boolean push(String user, String value) {
      return code.get(user).add(value);
    }

    // Metodo privato per la lettura del primo valore nella coda
    // all'offset specificato
    public String poll(String user) {
      String value = code.get(user).poll();
      if (value == null) {
	// Setto value a "" se vale null, per evitare errori e
	// problemi più avanti
	value = "";
      }
      return value;
    }

    // Metodo per la creazione di una nuova coda di messagi per l'utente
    public boolean create(String user) {
      // Aggiungo la nuova coda alla lista
      Queue<String> newQueue = new LinkedList<String>();
      return (code.put(user, newQueue) == newQueue);
    }

    // Metodo per la rimozione della coda di messagi per l'utente
    public boolean delete(String user) {
      // Rimuovo la coda all'offset specificato
      Queue<String> origin = code.get(user);
      boolean res = (code.remove(user) == origin);
      return res;
    }

    public String listUsers(String user) {
      StringBuilder listBuilder = new StringBuilder();
      for (String _user : code.keySet()) {
	if (_user != user) {
	  listBuilder.append(_user);
	  listBuilder.append(", ");
	}
      }
      String res = listBuilder.toString();
      if (res.length() > 2) {
	res = res.substring(0, res.length() - 2);
      }
      return res;
    }
  }

  private UsersDataStorage data = null;

  SecureService() {
    super();
    File file = new File("usersdata.ser");
    boolean exists = file.exists();
    if (file.exists() && file.isFile()) {
      try {
	FileInputStream fileIn = new FileInputStream("usersdata.ser");
	ObjectInputStream in = new ObjectInputStream(fileIn);
	data = (UsersDataStorage) in.readObject();
	in.close();
	fileIn.close();
      } catch (Exception e) {
	data = new UsersDataStorage();
      }
    } else {
      data = new UsersDataStorage();
    }
  }

  protected void finalize() {
    try {
      if (data != null) {
	FileOutputStream fileOut = new FileOutputStream("usersdata.ser");
	ObjectOutputStream out = new ObjectOutputStream(fileOut);
	out.writeObject(data);
	out.close();
	fileOut.close();
      }
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  // Comandi, WIP
  private Map<String, Integer> cmds = new HashMap<String, Integer>();

  // Li avevo immaginati piu' complessi ma si sono rivelati una linea
  // di codice a testa
  public String chatLogin(String user) {
    // metodo per la registarzione dell'utente e per la creazione
    // della coda (vedi create)
    try {
      return String.valueOf(data.create(user));
    } catch (Exception e) {
      // Nel caso in cui l'offset non contenga l'utente scelto
      // ossia l'utente non esiste
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      return sw.toString();
    }
  }

  public boolean chatLogout(String user) {
    // metodo per l'eliminazione dell'utente e della  relativa coda
    // (vedi delete)
    return data.delete(user);
  }

  // API Per la gestione dei comandi
  private int parseMsg(String message, String user) {
    int ret = 0;
    if (message.startsWith(": ")) {
      String FullCommand = message.substring(2);
      int cmdLength = FullCommand.length() - 1;
      String[] cmd = FullCommand.split(" ", -1);
      switch (cmd[0]) {
	case "listUsers":
	  String msg = data.listUsers(user);
	  ret = (data.push(user, msg)) ? 0 : 1;
	  break;
	default:
	  ret = -1;
      }
    } else {
      ret = 1;
    }
    return ret;
  }

  // API per l'invio di un messaggio
  // Gli argomenti di questo metodo sono di Stringhe, una contenente
  // il messaggio da inviare e una contenente l'utente a cui inviare
  // il messaggio
  public String sendMsg(String message, String user) {
    // try {
    /* if (parse(message, user)) {
      // Salvo l'offset assciato all'utente in una variabile
      // chiamata offset

      // Utilizzo intValue(); perche' l'offset a noi serve come
      // tipo primitivo ma, per qualche motivo non meglio
      // specificato, il costruttore di Map accetta solo oggetti
      // quindi dovo fare la conversione ad ogni utilizzo
      int offset = offsets.get(user).intValue();
      // Aggiorno il contenuto della coda con il nuovo messaggio
      // e lo faccio chiamndo il metodo QueuePush definito in
      // precedenza.
      code.push(offset, message);
      return "ok";
    } else {
      return "speciale";
    }
    // } catch (Exception e) {
    // Nel caso in cui l'offset non contenga l'utente scelto
    // ossia l'utente non esiste
    // return false;
    // return e.toString();
    // }*/
    try {
      return String.valueOf(parseMsg(message, user));
    } catch (Exception e) {
      // Nel caso in cui l'offset non contenga l'utente scelto
      // ossia l'utente non esiste
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
      return data.poll(user);
    } catch (Exception e) {
      // Nel caso in cui l'offset non contenga l'utente scelto
      // ossia l'utente non esiste
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      return sw.toString();
    }
  }
}
