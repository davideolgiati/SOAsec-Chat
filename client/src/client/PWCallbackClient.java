package client;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class PWCallbackClient implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			//UsernameToken auth
			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
				pc.setIdentifier(SecureServiceClient.username);
				pc.setPassword(SecureServiceClient.password);
			}
			//Signature/Encryption
			if (pc.getUsage() == WSPasswordCallback.DECRYPT || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
				// used to retrieve password for private key
				if ("client1".equals(pc.getIdentifier())) {
					pc.setPassword("password1");
				}
			}
		}
	}
}
