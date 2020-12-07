public class UserBusy extends Exception {
    private static final long serialVersionUID = 1L;

    public UserBusy(String user) {
        // Il messaggio si limita al nome utente, scelta di stile e funzinale
        // abbiamo osservato che in caso di stampa del messaggio l'errore
        // risulta più chiaro
        super(user);
    }
}
