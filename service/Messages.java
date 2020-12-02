import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import java.io.*;

class Messages implements Serializable {
    private static final long serialVersionUID = 21072020L;
    private Map<String, Queue<String>> messages = new HashMap<String, Queue<String>>();
    private final String path = System.getProperty("java.io.tmpdir") + "/messages.ser";

    // Costruttore

    public Messages() throws IOException {
        try {
            loadState();
        } catch (Exception e) {
            messages = new HashMap<String, Queue<String>>();
            generateState();
            saveState();
        }
    }

    // Metodi Privati

    // Gestione della Persistenza

    private void generateState() throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
    }

    private void saveState() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(messages);
        out.close();
        fileOut.close();
    }

    private void loadState() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        messages = (HashMap<String, Queue<String>>) in.readObject();
        in.close();
        fileIn.close();
    }

    // Decoratori HashMap

    public void _put(String user, String message) throws IOException, ClassNotFoundException {
        loadState();
        if(! messages.containsKey(user))
            create(user);
        Queue<String> tmp = messages.get(user);
        tmp.add(message);
        messages.put(user, tmp);
        saveState();
    }

    public String _get(String user) throws IOException, ClassNotFoundException {
        String msg = "";
        loadState();
        boolean test = messages.containsKey(user);
        if (test) {
            msg = messages.get(user).poll();
        }
        saveState();
        return msg;
    }

    private void create(String user) throws IOException, ClassNotFoundException {
        loadState();
        messages.put(user, new LinkedList<String>());
        saveState();
    }

    private void remove(String user) throws IOException, ClassNotFoundException {
        loadState();
        messages.remove(user);
        saveState();
    }

    // Metodi Publici

    // Metodo per la creazione di una nuova coda di messagi per l'utente
    public boolean addUser(String user) throws IOException, ClassNotFoundException {
        // Aggiungo la nuova coda alla lista
        create(user);
        return (messages.get(user) != null);
    }

    // Metodo per la rimozione della coda di messagi per l'utente
    public boolean deleteUser(String user) throws IOException, ClassNotFoundException {
        // Rimuovo la coda all'offset specificato
        remove(user);
        return (messages.get(user) == null);
    }

    public String listUsers(String user) throws NullPointerException, ClassNotFoundException, IOException {
        loadState();
        String _res = "";
        try {
        TreeSet<String> res = new TreeSet<String>();
        for (String _user : messages.keySet()) {
            if (! _user.equals(user))
                res.add(_user);
        }
        String list = res.toString();
        if (list.length() > 1){
            _res = list.substring(1, list.length() - 1);
        } else {
            _res = list;
        }
        } catch (Exception e) {
            _res = "";
        }
        return _res;
    }

    public boolean userExists(String user) throws ClassNotFoundException, IOException {
        loadState();
        return messages.keySet().contains(user);
    }
}