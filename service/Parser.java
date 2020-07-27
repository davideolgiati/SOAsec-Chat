package rpc.secure.chat;

class Parser {
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
		//todo
	}
    }

    public class ListUsers implements Command {
	public void execute(String user) {
		//todo
	}
    }

    public class OpenConversation implements Command {
	public void execute(String user) {
		//todo
	}
    }
}
