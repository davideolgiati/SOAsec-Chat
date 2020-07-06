/**
 * Secure Service implementation class
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Map;

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

    private Map<String, Integer> offsets = new Map<String, Integer>();

    // Metodo privato per l'inserimento di un valore nella coda
    // all'offset specificato
    private boolean QueuePush(int offset){
	// TODO
	// so gia come farlo pregno, lascialo a me
    }

    // API per l'invio di un messaggio
    // Gli argomenti di questo metodo sono di Stringhe, una contenente
    // il messaggio da inviare e una contenente l'utente a cui inviare
    // il messaggio
    public boolean send(String Message, String User) {
	// Come prima cosa controllo che l'utente a cui voglio inviare
	// il messaggio esista effettivamente
	if (!offsets.containsKey(User)) {
	    // Se non esite comunico al client l'errore
	    return false;
	} else {
	    // Se esiste salvo l'offset assciato all'utente in una
	    // variabile chiamata offset

	    // Utilizzo intValue(); perchè l'offset a noi serve come
	    // tipo primitivo ma, per qualche motivo non meglio
	    // specificato, il costruttore di Map accetta solo oggetti
	    // quindi dovo fare la conversione ad ogni utilizzo
	    int offset = offsets.get(User).intValue();
	    // Aggiorno il contenuto della coda con il nuovo messaggio
	    // e lo faccio  chiamndo il metodo QueuePush definito in
	    // precedenza.
	    return QueuePush(offset);
	}
    }
}
