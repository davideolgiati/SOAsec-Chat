/*
 * Eccezioni
 *
 * UserNotFound è un eccezione personalizzata utile, lato client, per
 * rilevare errori riguardo la presenza (assenza) dell'utente desiderato sul
 * server
 * 
 * La struttura dell'eccezione è standard. La creazione di un eccezione a se per
 * questo tipo di errore è stata una scelta dettata dal desiderio di avere più
 * chiarezza nella gestione degli errori
 */
public class UserNotFound extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotFound(String user) {
        // Il messaggio si limita al nome utente, scelta di stile e funzinale
        // abbiamo osservato che in caso di stampa del messaggio l'errore
        // risulta più chiaro
        super(user);
    }
}