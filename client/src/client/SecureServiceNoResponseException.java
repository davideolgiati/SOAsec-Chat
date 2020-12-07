
/**
 * SecureServiceNoResponseException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceNoResponseException extends java.lang.Exception{

    private static final long serialVersionUID = 1607200261379L;
    
    private client.SecureServiceStub.SecureServiceNoResponse faultMessage;

    
        public SecureServiceNoResponseException() {
            super("SecureServiceNoResponseException");
        }

        public SecureServiceNoResponseException(java.lang.String s) {
           super(s);
        }

        public SecureServiceNoResponseException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceNoResponseException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceNoResponse msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceNoResponse getFaultMessage(){
       return faultMessage;
    }
}
    