package chatUtils;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Parser implements Serializable {
    private static final long serialVersionUID = 22072020L;
    private Data data = null;
    private String tmpfile = System.getProperty("java.io.tmpdir") + "/usersdata.ser";

    private interface Command {
	public void execute(String[] user);
    }

    private class CloseConversation implements Command {
	public void execute(String[] user) {
	    //todo
	}
    }

    private class ChatLogout implements Command {
	public void execute(String[] user) {
	    data.delete(user[0]);
	}
    }

    private class ListUsers implements Command {
	public void execute(String[] user) {
	    parse(data.usersToString(""), user[0]);
	}
    }

    private class OpenConversation implements Command {
	public void execute(String[] user) {

	}
    }

    private Map<String, Command> cmds = new HashMap<>();

    public Parser() {
	try {
	    File file = new File(tmpfile);
	    if (file.exists() && file.isFile()) {
		FileInputStream fileIn = new FileInputStream(tmpfile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		data = (Data) in.readObject();
		in.close();
		fileIn.close();
	    } else {
		file.createNewFile();
		data = new Data();
	    }
	} catch (Exception e) {
	    data = new Data();
	}

	cmds.put(":listUsers", new ListUsers());
	cmds.put(":openConversation", new OpenConversation());
	cmds.put(":closeConversation", new CloseConversation());
	cmds.put(":chatLogout", new ChatLogout());
    }

    private void dump() {
	try {
	    FileOutputStream fileOut = new FileOutputStream(tmpfile);
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(data);
	    out.close();
	    fileOut.close();
	} catch (IOException i) {
	    i.printStackTrace();
	}
    }

    public String parse(String message, String user) {
	//variabile contenente tutte le parole del messaggio
	String[] stringList = new String[0];
	String result = "";
	System.out.println("[INFO] inizio metodo chatUtils.Parser.parse");
	if(message == null || message.equals("")) {
	    System.out.println("[INFO] rilevata stringa vuota");
	    //questo e' il caso in cui l'utente ha premuto invio
	    //senza aver scritto niente e quindi il programma
	    //non dovra' fare niente
	} else {
	    stringList = message.split(" ");
	    if(message.charAt(0) != ':') {
		System.out.println("[INFO] messaggio non contenete comandi");
		//se il messaggio non contiene un comando
		//allora...
		data.push(user, message);
	    } else {
		System.out.println("[INFO] possibile comando rilevato");
		//esecuzione del comando
		String command = stringList[0];
		if(cmds.containsKey(command)) {
		    System.out.println("[INFO] Comando " + command + " riconosciuto");
		    Command cmd = cmds.get(command);
		    System.out.println("[INFO] Inizio esecuzione comando");
		    if (stringList.length > 1) {
			cmd.execute(new String[] {user, stringList[1]});
		    } else {
			cmd.execute(new String[] {user});
		    }
		} else {
		    System.out.println("[INFO] Comando " + command + " non riconosciuto");
		    result = "Comando sconosciuto!";
		}
	    }
	}
	dump();
	return result;
    }

    public String getFor(String user){
	String res = data.poll(user);
	dump();
	return res;
    }

    public void pLogin(String user) {
	data.create(user);
	dump();
    }
}
