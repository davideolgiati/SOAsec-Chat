import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import javax.crypto.spec.SecretKeySpec;

public class Requests implements Serializable {
    private static final long serialVersionUID = 22072020L;
    private Map<String, Boolean> requests = new HashMap<String, Boolean>();
    private final String path = System.getProperty("java.io.tmpdir") + "/requests.ser";
    private SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
            "Blowfish");
    private Cipher cipher;

    // Costruttore

    public Requests() throws IOException, InvalidKeyException, IllegalBlockSizeException, NoSuchAlgorithmException,
            NoSuchPaddingException {
        cipher = Cipher.getInstance("Blowfish");
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

    private void saveState() throws IOException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key64);
        SealedObject sealedObject = new SealedObject((Serializable) requests, cipher);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)), cipher);
        ObjectOutputStream outputStream = new ObjectOutputStream(cipherOutputStream);
        outputStream.writeObject(sealedObject);
        outputStream.close();
    }

    private void loadState()
            throws IOException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, key64);
        CipherInputStream cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(path)), cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
        SealedObject sealedObject = (SealedObject) inputStream.readObject();
        requests = (Map<String, Boolean>) sealedObject.getObject(cipher);
    }

    // Decoratori HashMap

    private void put(String key, Boolean value) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        requests.put(key, value);
        saveState();
    }

    private Boolean get(String key) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        return requests.get(key);
    }

    private void remove(String key) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        requests.remove(key);
        saveState();
    }

    // Metodi Publici

    public void request(String me, String myFriend) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
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

    public boolean accept(String me, String myFriend) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        String key = myFriend + "##" + me;
        put(key, true);
        boolean ret = get(key);
        remove(key);
        remove(me + "##" + myFriend);
        return ret;
    }

    public boolean deny(String me, String myFriend) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
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
