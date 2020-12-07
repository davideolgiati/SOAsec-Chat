import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests implements Serializable {
    private static final long serialVersionUID = 22072020L;
    private Map<String, Boolean> requests = new HashMap<String, Boolean>();
    private final String path = System.getProperty("java.io.tmpdir") + "/requests.ser";

    // Costruttore

    public Requests() throws StorageError {
        try {
            loadState();
        } catch (Exception e) {
            requests = new HashMap<String, Boolean>();
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
                throw new StorageError("Requests", "IOException");
            }
    }

    private void saveState() throws StorageError {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(requests);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new StorageError("Requests", "IOException");
        }
    }

    private void loadState() throws StorageError {
        try {
            FileInputStream fileIn = null;
            ObjectInputStream in = null;
            fileIn = new FileInputStream(path);
            in = new ObjectInputStream(fileIn);
            requests = (HashMap<String, Boolean>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            throw new StorageError("Requests", "FileNotFoundException");
        } catch (IOException e) {
            throw new StorageError("Requests", "IOException");
        } catch (ClassNotFoundException e) {
            throw new StorageError("Requests", "ClassNotFoundException");
        }
    }

    // Decoratori HashMap

    private void put(String key, Boolean value) throws StorageError {
        loadState();
        requests.put(key, value);
        saveState();
    }

    private Boolean get(String key) throws StorageError {
        loadState();
        return requests.get(key);
    }

    private void remove(String key) throws StorageError {
        loadState();
        requests.remove(key);
        saveState();
    }

    // Metodi Publici

    public void request(String me, String myFriend) throws StorageError {
        String key = me + "##" + myFriend;
        put(key, null);
    }

    public List<String> checkForRequests(String me) throws StorageError {
        loadState();
        List<String> res = new ArrayList<String>();
        String suffix = "##" + me;
        for (String key : requests.keySet()) {
            if (key.endsWith(suffix)) {
                res.add(key.substring(0, suffix.length() - 2));
            }
        }
        return res;
    }

    public boolean accept(String me, String myFriend) throws StorageError {
        String key = myFriend + "##" + me;
        put(key, true);
        boolean ret = get(key);
        return ret;
    }

    public boolean deny(String me, String myFriend) throws StorageError {
        String key = myFriend + "##" + me;
        put(key, false);
        boolean ret = (get(key) == false);
        remove(key);
        remove(me + "##" + myFriend);
        return ret;
    }

    public boolean status(String me, String myFriend) throws StorageError, NoResponse {
        String key = me + "##" + myFriend;
        Boolean res = get(key);
        if (res == null) {
            throw new NoResponse(myFriend);
        } else {
            return res.booleanValue();
        }
    }
}
