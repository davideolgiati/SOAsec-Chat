import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

import java.util.Map;
import java.util.HashMap;

public class PWCallbackService implements CallbackHandler {
	private Map<String, String> passwords = new HashMap<String, String>();
	
	public PWCallbackService() {
	      passwords.put("client1", "password1");
	      passwords.put("client2", "password2");
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			//UsernameToken auth
			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {

				// You must set a password for the user, AXIS2 would compare
				// the password with the password sent by client, if they match
				// message will be processed. Any mismatch in password will
				// result in a SOAP Fault.

				if (!passwords.containsKey(pc.getIdentifier())) {
					throw new UnsupportedCallbackException(callbacks[i], "Utente sconosciuto!");
				} else {
					switch(pc.getIdentifier()) {
						case "client1":
							pc.setPassword(passwords.get("client1"));
							break;
						case "client2":
							pc.setPassword(passwords.get("client2"));
							break;
					}
				}
			}
			//Signature/Encryption
			if (pc.getUsage() == WSPasswordCallback.DECRYPT || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
				// used to retrieve password for private key
				if ("service".equals(pc.getIdentifier())) {
					pc.setPassword("password");
				}
			}
		}
	}
}
