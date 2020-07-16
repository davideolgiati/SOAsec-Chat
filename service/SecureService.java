import java.util.HashMap;
import java.util.Map;

public class SecureService {
  private QueueList code = new QueueList();

  // Oltre alle code ci serve anche una struttura che tenga traccia
  // degli offset delle code dei singoli utenti.
  // La mia scelta si orienta qui verso l'utilizzo di una Map per
  // la possibilità si assegnare ad una stringa un corrispondente
  // valore numerico username -> offset

  // N.B. QUESTO E' IL MODO PIU' ELEGANTE MA NECESSITA DI UN METODO
  //      CHE SCALI GLI OFFSET OGNI VOLTA CHE UN UTENTE NON E' PIU'
  //      LOGGATO AL SERVER

  private Map<String, Integer> offsets = new HashMap<String, Integer>();

  // Per comodita' tengo anche una variabile che mi dice quanti
  // elementi ho nella lista

  private int ListSize = 0;

  // Comandi, WIP
  private static Map<String, Integer> cmds;

  static {
    cmds = new HashMap<String, Integer>();
    cmds.put("listUsers", 0);
    cmds.put("chatWith", 1);
  };

  // Li avevo immaginati piu' complessi ma si sono rivelati una linea
  // di codice a testa
  public boolean login(String user) {
    // metodo per la registarzione dell'utente e per la creazione
    // della coda (vedi create)
    boolean ret = code.create();
    if (ret) {
      offsets.put(user, ListSize);
      ListSize++;
    }
    return ret;
  }

  public boolean logout(String user) {
    // metodo per l'eliminazione dell'utente e della  relativa coda
    // (vedi delete)
    int offset = offsets.get(user).intValue();
    boolean ret = code.delete(offset);

    if (ret) {
      offsets.remove(user);
      // In caso non ci siano stati problemi diminuisco
      // l'indice di offset
      ListSize--;
      // E aggiorno i valori di offset per gli utenti ancora
      // attivi
      for (String toTestUser : offsets.keySet()) {
	// per ogni user leggo l'offset associato
	int tmp = offsets.get(toTestUser).intValue();
	// controllo se l'offset e' piu' grande di quello
	// associato all'utente che si e' scollegato
	if (tmp > offset) {
	  // aggiorno l'offset a -1
	  offsets.put(toTestUser, (tmp - 1));
	}
      }
    }
    return ret;
  }

  // API Per la gestione dei comandi
  private boolean parse(String message, String user) {
    boolean ret = false;
    if (message.startsWith(": ")) { // controllo se è un messaggio di
      // controllo
      String FullCommand = message.substring(2); // rimuovo i caratteri
      // inutili
      int cmdLength = FullCommand.length();
      while (FullCommand.startsWith(" ")) {
	FullCommand = FullCommand.substring(1); // semplice trim degli
	// spazi
      }
      while (FullCommand.endsWith(" ")) {
	cmdLength -= 1;
	FullCommand = FullCommand.substring(0, cmdLength);
      }
      String[] cmd = FullCommand.split(" ", -1);
      if (cmds.containsKey(cmd[0]) && cmds.get(cmd[0]) == (cmd.length - 1)) {
	switch (cmd[0]) {
	  case "listUsers":
	    String msg = "";
	    StringBuilder sb = new StringBuilder();
	    for (String key : offsets.keySet()) {
	      if (key != user) {
		sb.append(key).append("\n");
	      }
	    }
	    msg = sb.deleteCharAt(sb.length() - 1).toString();
	    int offset = offsets.get(user).intValue();
	    ret = code.push(offset, msg);
	    break;
	  default:
	    System.out.println("no match");
	}
      }
    }
    return ret;
  }

  // API per l'invio di un messaggio
  // Gli argomenti di questo metodo sono di Stringhe, una contenente
  // il messaggio da inviare e una contenente l'utente a cui inviare
  // il messaggio
  public boolean send(String message, String user) {
    try {
      if (parse(message, user)) {
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
	return code.push(offset, message);
      } else {
	return true;
      }
    } catch (Exception e) {
      // Nel caso in cui l'offset non contenga l'utente scelto
      // ossia l'utente non esiste
      return false;
    }
  }

  public String recive(String user) {
    try {
      // Se esiste salvo l'offset assciato all'utente in una
      // variabile chiamata offset

      // Utilizzo intValue(); perche' l'offset a noi serve come
      // tipo primitivo ma, per qualche motivo non meglio
      // specificato, il costruttore di Map accetta solo oggetti
      // quindi dovo fare la conversione ad ogni utilizzo
      int offset = offsets.get(user).intValue();
      // Aggiorno il contenuto della coda e lo faccio chiamndo
      // il metodo QueuePull definito in precedenza.
      return code.poll(offset);
    } catch (Exception e) {
      // Nel caso in cui l'offset non contenga l'utente scelto
      // ossia l'utente non esiste
      return "Utente inesistente";
    }
  }
}
