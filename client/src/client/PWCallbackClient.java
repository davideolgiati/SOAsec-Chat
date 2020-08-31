package client;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class PWCallbackClient implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			/*
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			//UsernameToken auth/Signature/Encryption
			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN || pc.getUsage() == WSPasswordCallback.DECRYPT || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
				pc.setIdentifier(SecureServiceClient.username);
				pc.setPassword(SecureServiceClient.password);
			}
			*/

			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback pc=(WSPasswordCallback)callbacks[i];
				if (pc.getIdentifier().equals("client1")) {
					pc.setPassword("password1");
				} else {
					throw new UnsupportedCallbackException(callbacks[i], "Unknown user");
				}
			} else {
				throw new UnsupportedCallbackException(callbacks[i],"Unrecognized Callback");
			}
		}
	}
}
