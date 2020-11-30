import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class Messages implements Serializable {
    private static final long serialVersionUID = 21072020L;
    private Map<String, Queue<String>> messages = new HashMap<String, Queue<String>>();
    private final String path = System.getProperty("java.io.tmpdir") + "/messages.ser";
    private SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
            "Blowfish");
    private Cipher cipher;

    // Costruttore

    public Messages() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException {
        cipher = Cipher.getInstance("Blowfish");
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

    private void saveState() throws IOException, InvalidKeyException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key64);
        SealedObject sealedObject = new SealedObject((Serializable) messages, cipher);
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
        messages = (Map<String, Queue<String>>) sealedObject.getObject(cipher);
    }

    // Decoratori HashMap

    public void put(String user, String message) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        messages.get(user).add(message);
        saveState();
    }

    public String get(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        String message = messages.get(user).poll();
        saveState();
        if (message.equals(null)){
            message = "";
        }
        return message;
    }

    private void create(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        if (messages.get(user) == null) {
            messages.put(user, new LinkedList<String>());
            saveState();
        }
    }

    private void remove(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        messages.remove(user);
        saveState();
    }

    // Metodi Publici

    // Metodo per la creazione di una nuova coda di messagi per l'utente
    public boolean addUser(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // Aggiungo la nuova coda alla lista
        create(user);
        return (messages.get(user) != null);
    }

    // Metodo per la rimozione della coda di messagi per l'utente
    public boolean deleteUser(String user) throws IOException, ClassNotFoundException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // Rimuovo la coda all'offset specificato
        remove(user);
        return (messages.get(user) == null);
    }

    public String listUsers() throws NullPointerException, ClassNotFoundException, IOException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        TreeSet<String> res = new TreeSet<String>(); 
        for (String _user : messages.keySet()) {
            res.add(_user);
        }
        String list = res.toString();
        return list.substring(1, list.length() - 1);
    }

    public boolean userExists(String user) throws ClassNotFoundException, IOException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        loadState();
        return messages.keySet().contains(user);
    }
}
