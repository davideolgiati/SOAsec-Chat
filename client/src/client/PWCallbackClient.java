import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class PWCallbackClient implements CallbackHandler {
   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
	   for (int i = 0; i < callbacks.length; i++) {
		   WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];

		   if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
			   pc.setIdentifier(SecureServiceClient.username);
			   pc.setPassword(SecureServiceClient.password);
		   }
	   }
      
      /*
       * se si utilizza questa implementazione, l'Identifier della richiesta viene settato nell'xml
       * del client attraverso il tag <user> (come fa a lezione).
       * In questo modo pero' sarebbe inutile fare specificare all'utente il suo username e password
       * in fase di login
       *
      WSPasswordCallback pc = (WSPasswordCallback)callbacks[0];
      
      switch(pc.getIdentifier()) {
      	case "client1":
      		pc.setPassword("password1");
      		break;
      	case "client2":
      		pc.setPassword("password2");
      		break;
      	default:
      		pc.setPassword("wrong password");
      }
      */
   }
}
