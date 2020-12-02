import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class States implements Serializable {
    private static final long serialVersionUID = 23072020L;
    private Map<String, Boolean> state = new HashMap<String, Boolean>();
    private final String path = System.getProperty("java.io.tmpdir") + "/states.ser";

    // Costruttore

    public States() throws IOException {
        try {
            loadState();
        } catch (Exception e) {
            state = new HashMap<String, Boolean>();
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
        out.writeObject(state);
        out.close();
        fileOut.close();
    }

    private void loadState() throws IOException, ClassNotFoundException {
        if (new File(path).exists()) {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            state = (Map<String, Boolean>) in.readObject();
            in.close();
            fileIn.close();
        } else {
            state = new HashMap<String, Boolean>();
            generateState();
            saveState();
        }
    }

    // Decoratori HashMap

    private void put(String key, Boolean value)
            throws IOException, ClassNotFoundException {
        loadState();
        state.put(key, value);
        saveState();
    }

    private boolean get(String key)
            throws IOException, ClassNotFoundException {
        loadState();
        Boolean ret = state.get(key);
        return (ret == null) ? false : ret;
    }

    // Metodi Publici

    public void remove(String key)
            throws IOException, ClassNotFoundException {
        loadState();
        state.remove(key);
        saveState();
    }

    public boolean ready(String user) throws IOException, ClassNotFoundException {
        try {
            put(user, true);
            return get(user);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean busy(String user) throws IOException, ClassNotFoundException {
        try {
            put(user, false);
            return get(user) == false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isReady(String user)
            throws IOException, ClassNotFoundException {
        boolean userState = get(user);
        return userState;
    }
}