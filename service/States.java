import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public States() throws StorageError {
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

    private void generateState() throws StorageError {
        File file = new File(path);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new StorageError("States", "IOException");
            }
    }

    private void saveState() throws StorageError {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new StorageError("States", "IOException");
        }
    }

    private void loadState() throws StorageError {
        try {
            FileInputStream fileIn = null;
            ObjectInputStream in = null;
            fileIn = new FileInputStream(path);
            in = new ObjectInputStream(fileIn);
            state = (HashMap<String, Boolean>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            throw new StorageError("States", "FileNotFoundException");
        } catch (IOException e) {
            throw new StorageError("States", "IOException");
        } catch (ClassNotFoundException e) {
            throw new StorageError("States", "ClassNotFoundException");
        }
    }

    // Decoratori HashMap

    private void put(String key, Boolean value) throws StorageError {
        loadState();
        state.put(key, value);
        saveState();
    }

    private boolean get(String key) throws StorageError {
        loadState();
        Boolean ret = state.get(key);
        return (ret == null) ? false : ret;
    }

    // Metodi Publici

    public void remove(String key) throws StorageError {
        loadState();
        state.remove(key);
        saveState();
    }

    public boolean ready(String user) throws StorageError {
        put(user, true);
        return get(user);
    }

    public boolean busy(String user) throws StorageError {
        put(user, false);
        return get(user) == false;
    }

    public boolean isReady(String user) throws StorageError {
        boolean userState = get(user);
        return userState;
    }
}