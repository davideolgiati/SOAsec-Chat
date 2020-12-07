
/**
 * SecureServiceUserNotFoundException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceUserNotFoundException extends java.lang.Exception{

    private static final long serialVersionUID = 1607200261408L;
    
    private client.SecureServiceStub.SecureServiceUserNotFound faultMessage;

    
        public SecureServiceUserNotFoundException() {
            super("SecureServiceUserNotFoundException");
        }

        public SecureServiceUserNotFoundException(java.lang.String s) {
           super(s);
        }

        public SecureServiceUserNotFoundException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceUserNotFoundException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceUserNotFound msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceUserNotFound getFaultMessage(){
       return faultMessage;
    }
}
    