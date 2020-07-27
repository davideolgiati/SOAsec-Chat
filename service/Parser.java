package rpc.secure.chat;

import java.util.HashMap;
import java.util.Map;
import java.io.*;


class Parser implements Serializable {
    private static final long serialVersionUID = 22072020L;
    private Data data = null;
    private String tmpfile = System.getProperty("java.io.tmpdir") + "/usersdata.ser";

    private interface Command {
	public void execute(String user);
    }

    public class CloseConversation implements Command {
	public void execute(String user) {
	    //todo
	}
    }

    public class ChatLogout implements Command {
	public void execute(String user) {
	    data.delete(user);
	}
    }

    public class ChatLogin implements Command {
	public void execute(String user) {
	    data.create(user);
	}
    }

    public class ListUsers implements Command {
	public void execute(String user) {
	    data.push(data.usersToString(user), user);
	}
    }

    public class OpenConversation implements Command {
	public void execute(String user) {
		//todo
	}
    }

    private Map<String, Command> cmds;

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
	cmds.put(":chatLogin", new ChatLogin());
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

	if(message == null || message.equals("")) {
	    //questo e' il caso in cui l'utente ha premuto invio
	    //senza aver scritto niente e quindi il programma
	    //non dovra' fare niente
	} else {
	    stringList = message.split(" ");
	    if(message.charAt(0) != ':') {
		//se il messaggio non contiene un comando
		//allora...
		data.push(user, message);
	    } else {
		//esecuzione del comando
		String command = stringList[0].toLowerCase();
		if(cmds.containsKey(command)) {
		    Command cmd = cmds.get(command);
		    cmd.execute(user);
		} else {
		    result = "Comando sconosciuto!";
		}
	    }
	}
	return result;
    }

    public String getFor(String user){
	return data.poll(user);
    }
}
