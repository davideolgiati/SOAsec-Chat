import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class PWCallbackService implements CallbackHandler{
   private Map<String, String> passwords = new HashMap<String, String>();
   
   public KeystorePasswordCallback(){
      passwords.put("client1", "password1");
      passwords.put("client2", "password2");
   }
   
   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException{
      for (int i = 0; i < callbacks.length; i++){
    	  boolean refuse = false;
    	  WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
    	  if(!passwords.containsKey(pc.getIdentifier())) {
    		  System.out.println("Utente non registrato!");
    		  refuse = true;
    	  }
    	  else if(!passwords.get(pc.getIdentifier()).equals(pc.getPassword())){
    		  System.out.println("Password errata!");
    		  refuse = true;
    	  }
    	  if(refuse){
    		  throw new IOException();
    	  }
      }
   }
}

