import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SecureService {
  private class QueueList {
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

    private List<Queue<String>> code = new ArrayList<Queue<String>>();

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
    public boolean push(int offset, String value) {
      // Salvo l'elemento da modificare nella variabile tmp
      Queue<String> tmp = code.get(offset);
      // dato che il metodo add ritorna un valore booleano, uso
      // questo valore come indicatore di successo dell'operazione
      // di aggiornamneto
      boolean result = tmp.add(value);
      if (result) {
	// Solo se l'operazione e' andata a buon fine aggiorno la
	// lista
	Queue<String> ret = code.set(offset, tmp);
	result = (ret == tmp);
      }
      return result;
    }

    // Metodo privato per la lettura del primo valore nella coda
    // all'offset specificato
    public String poll(int offset) {
      // Salvo l'elemento da modificare nella variabile tmp
      Queue<String> tmp = code.get(offset);
      // dato che il metodo add ritorna un valore booleano, uso
      // questo valore come indicatore di successo dell'operazione
      // di aggiornamneto
      String value = tmp.poll();
      if (value != null) {
	// Solo se l'operazione è andata a buon fine aggiorno la
	// lista
	code.set(offset, tmp);
      } else {
	// Setto value a "" se vale null, per evitare errori e
	// problemi più avanti
	value = "";
      }
      return value;
    }

    // Metodo per la creazione di una nuova coda di messagi per l'utente
    public boolean create() {
      // Come prima cosa creo una nuova coda vuota
      Queue<String> newQueue = new LinkedList<String>();
      // Aggiungo la nuova coda alla lista
      return code.add(newQueue);
    }

    // Metodo per la rimozione della coda di messagi per l'utente
    public boolean delete(int offset) {
      // Rimuovo la coda all'offset specificato
      Queue<String> origin = code.get(offset);
      boolean res = (code.remove(offset) == origin);
      return res;
    }
  }

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
