/**
 * Secure Service implementation class
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class SecureService {
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

    // Oltre alle code ci serve anche una struttura che tenga traccia
    // degli offset delle code dei singoli utenti.
    // La mia scelta si orienta qui verso l'utilizzo di una Map per
    // la possibilità si assegnare ad una stringa un corrispondente
    // valore numerico username -> offset

    // N.B. QUESTO È IL MODO PIÙ ELEGANTE MA NECESSITA DI UN METODO
    //      CHE SCALI GLI OFFSET OGNI VOLTA CHE UN UTENTE NON È PIÙ
    //      LOGGATO AL SERVER

    private Map<String, Integer> offsets = new HashMap<String, Integer>();

    // Per comodità tengo anche una variabile che mi dice quanti
    // elementi ho nella lista

    private int ListSize = 0;

    // Per aggiornare lo stato delle code (sia push che pop) dobbiamo
    // salvare le code originali in una variabile temporanea, aggiornare
    // e poi riscrivere i valori finali

    // STEP

    // 1. LEGGO LA CODA
    // 2. MODIFICO
    // 3. SE LA MODIFICA È COMPLETA VAI A 4. ALTRIMENTI TORNA 2.
    // 4. SCRIVO IL VALORE MODIFICATO NELLA POSIZIONE ORIGINALE

    // Metodo privato per l'inserimento di un valore nella coda
    // all'offset specificato
    private void QueuePush(int offset, String value){
    	// Salvo l'elemento da modificare nella variabile tmp
    	Queue<String> tmp = code.get(offset);
    	// dato che il metodo add ritorna un valore booleano, uso
    	// questo valore come indicatore di successo dell'operazione
    	// di aggiornamneto
    	boolean result = tmp.add(value);
    	if (result) {
    		// Solo se l'operazione è andata a buon fine aggiorno la
    		// lista
    		code.set(offset, tmp);
    	}
    }

    // Metodo privato per la lettura del primo valore nella coda
    // all'offset specificato
    private String QueuePull(int offset){
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
    private boolean CreateQueue(String user){
    	// Come prima cosa controllo che non esista già un offset
    	// associato all'utente
    	if (!offsets.containsKey(user)) {
    		// Se non c'è nessun offset associato procedo

    		// Come prima cosa creo una nuova coda vuota
    		Queue<String> newQueue = new LinkedList<String>();
    		// Aggiungo la nuova coda alla lista
    		boolean res = code.add(newQueue);
    		// Controllo se l'aggiunta della coda alla lista è andata
    		// a buon fine
    		if (res) {
    			// In caso non ci siano stati problemi aumento l'indice
    			// di offset
    			ListSize++;
    			// E associo all'username il nuovo offset
    			offsets.put(user, ListSize);
    		}
    	}
    	// se l'sername è già nella mapa degli offest ritorno errore
    	return false;
    }

    private boolean registerUser(String user){
	// metodo per la registarzione dell'utente e per la creazione
	// della coda (vedi CreateQueue)
    	return true;
    }

    private boolean deleteUser(String user){
	// metodo per l'eliminazione dell'utente e della  relativa coda
	// (vedi DeleteQueue [da creare])
    	return true;
    }

    /*
     * TODO
     * 1) creare il metodo per registare l'utente
     * 3) creare il metodo per il logout
     * 4) creare il metodo per rimuovere una lista dalla coda
     */

    // API per l'invio di un messaggio
    // Gli argomenti di questo metodo sono di Stringhe, una contenente
    // il messaggio da inviare e una contenente l'utente a cui inviare
    // il messaggio
    public void send(String message, String user) throws NoUserFoundException {
    	// Come prima cosa controllo che l'utente a cui voglio inviare
    	// il messaggio esista effettivamente
    	if (!offsets.containsKey(user)) {
    		// Se non esite comunico al client l'errore
    		throw new NoUserFoundException("Utente inesistente");
    	} else {
    		// Se esiste salvo l'offset assciato all'utente in una
    		// variabile chiamata offset

    		// Utilizzo intValue(); perchè l'offset a noi serve come
    		// tipo primitivo ma, per qualche motivo non meglio
    		// specificato, il costruttore di Map accetta solo oggetti
    		// quindi dovo fare la conversione ad ogni utilizzo
    		int offset = offsets.get(user).intValue();
    		// Aggiorno il contenuto della coda con il nuovo messaggio
    		// e lo faccio chiamndo il metodo QueuePush definito in
    		// precedenza.
    		QueuePush(offset, message);
    	}
    }

    public String recive(String user) throws NoUserFoundException {
    	// Come prima cosa controllo che l'utente che richiede
    	// il messaggio esista effettivamente
    	if (!offsets.containsKey(user)) {
    		// Se non esite comunico al client l'errore
    		throw new NoUserFoundException("Utente inesistente");
    	} else {
    		// Se esiste salvo l'offset assciato all'utente in una
    		// variabile chiamata offset

    		// Utilizzo intValue(); perchè l'offset a noi serve come
    		// tipo primitivo ma, per qualche motivo non meglio
    		// specificato, il costruttore di Map accetta solo oggetti
    		// quindi dovo fare la conversione ad ogni utilizzo
    		int offset = offsets.get(user).intValue();
    		// Aggiorno il contenuto della coda e lo faccio chiamndo
    		//il metodo QueuePull definito in precedenza.
    		return QueuePull(offset);
    	}
    }
}

