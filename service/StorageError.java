public class StorageError extends Exception {
    private static final long serialVersionUID = 1L;

    public StorageError(String _class, String _type) {
        // Il messaggio si limita al nome utente, scelta di stile e funzinale
        // abbiamo osservato che in caso di stampa del messaggio l'errore
        // risulta più chiaro
        super("errore nel meccanismo di persistenza della classe " + _class + " del tipo " + _type);
    }
}