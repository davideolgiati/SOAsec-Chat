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
import java.util.HashMap;
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

public class States implements Serializable {
    private static final long serialVersionUID = 23072020L;
    private Map<String, Boolean> state = new HashMap<String, Boolean>();
    private final String path = System.getProperty("java.io.tmpdir") + "/states.ser";
    private SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
            "Blowfish");
    private Cipher cipher;

    // Costruttore

    public States() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException {
        cipher = Cipher.getInstance("Blowfish");
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

    private void saveState() throws IOException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key64);
        SealedObject sealedObject = new SealedObject((Serializable) state, cipher);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)), cipher);
        ObjectOutputStream outputStream = new ObjectOutputStream(cipherOutputStream);
        outputStream.writeObject(sealedObject);
        outputStream.close();
    }

    private void loadState() throws IOException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key64);
        CipherInputStream cipherInputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(path)), cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
        SealedObject sealedObject = (SealedObject) inputStream.readObject();
        state = (Map<String, Boolean>) sealedObject.getObject(cipher);
    }

    // Decoratori HashMap

    private void put(String key, Boolean value) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        state.put(key, value);
        saveState();
    }

    private boolean get(String key) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        Boolean ret = state.get(key);
        return (ret == null) ? false : ret;
    }

    // Metodi Publici

    public void remove(String key) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
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

    public boolean isReady(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        boolean userState = get(user);
        return userState;
    }
}