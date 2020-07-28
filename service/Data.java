package chatUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.io.*;

class Data implements Serializable {
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

    private Map<String, Queue<String>> code = new HashMap<>();

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
	Queue<String> coda = code.get(user);
	return coda.add(value);
    }

    // Metodo privato per la lettura del primo valore nella coda
    // all'offset specificato
    public String poll(String user) {
	Queue<String> coda = code.get(user);
	String value = coda.poll();
	if (value == null) {
	    // Setto value a "" se vagit commit --amendle null, per evitare errori e
	    // problemi più avanti
	    value = "";
	}
	return value;
    }

    // Metodo per la creazione di una nuova coda di messagi per l'utente
    public boolean create(String user) {
	// Aggiungo la nuova coda alla lista
	code.put(user, new LinkedList<String>());
	return true; //tofix
    }

    // Metodo per la rimozione della coda di messagi per l'utente
    public boolean delete(String user) {
	// Rimuovo la coda all'offset specificato
	Queue<String> origin = code.get(user);
	boolean res = (code.remove(user) == origin);
	return res;
    }

    public String usersToString(String user) {
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
