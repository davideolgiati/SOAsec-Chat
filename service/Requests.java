import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Requests implements Serializable {
    private static final long serialVersionUID = 22072020L;
    private Map<String, Boolean> requests = new HashMap<String, Boolean>();
    private final String path = System.getProperty("java.io.tmpdir") + "/requests.ser";
    private SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x00, 0x01,
            0x02, 0x03, 0x04, 0x05, 0x06, 0x07 }, "AES");
    private byte[] iv = { 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
            0x17 };

    // Costruttore

    public Requests() throws IOException {
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

    private void generateState() throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
    }

    private void saveState() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(requests);
        out.close();
        fileOut.close();
    }

    private void loadState() throws IOException, ClassNotFoundException {
        if (new File(path).exists()) {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            requests = (Map<String, Boolean>) in.readObject();
            in.close();
            fileIn.close();
        } else {
            requests = new HashMap<String, Boolean>();
            generateState();
            saveState();
        }
    }

    // Decoratori HashMap

    private void put(String key, Boolean value) throws IOException, ClassNotFoundException {
        loadState();
        requests.put(key, value);
        saveState();
    }

    private Boolean get(String key) throws IOException, ClassNotFoundException {
        loadState();
        return requests.get(key);
    }

    private void remove(String key) throws IOException, ClassNotFoundException {
        loadState();
        requests.remove(key);
        saveState();
    }

    // Metodi Publici

    public void request(String me, String myFriend) throws IOException, ClassNotFoundException {
        String key = me + "##" + myFriend;
        put(key, null);
    }

    public List<String> checkForRequests(String me) throws IOException, ClassNotFoundException {
        List<String> res = new ArrayList<String>();
        String suffix = "##" + me;
        for (String key : requests.keySet()) {
            if (key.endsWith(suffix)) {
                res.add(key.substring(0, suffix.length() - 2));
            }
        }
        return res;
    }

    public boolean accept(String me, String myFriend) throws IOException, ClassNotFoundException {
        String key = myFriend + "##" + me;
        put(key, true);
        boolean ret = get(key);
        return ret;
    }

    public boolean deny(String me, String myFriend) throws IOException, ClassNotFoundException {
        String key = myFriend + "##" + me;
        put(key, false);
        boolean ret = (get(key) == false);
        remove(key);
        remove(me + "##" + myFriend);
        return ret;
    }

    public boolean status(String me, String myFriend) throws Exception {
        String key = me + "##" + myFriend;
        Boolean res = get(key);
        if (res == null) {
            throw new Exception("in attesa di risposta dall'utente");
        } else {
            return res.booleanValue();
        }
    }
}
