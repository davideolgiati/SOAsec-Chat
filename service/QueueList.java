import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueList {
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
    boolean res = code.remove(offset) == origin;
    return res;
  }
}
