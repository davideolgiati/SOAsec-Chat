
/**
 * SecureServiceStorageErrorException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceStorageErrorException extends java.lang.Exception{

    private static final long serialVersionUID = 1607200261394L;
    
    private client.SecureServiceStub.SecureServiceStorageError faultMessage;

    
        public SecureServiceStorageErrorException() {
            super("SecureServiceStorageErrorException");
        }

        public SecureServiceStorageErrorException(java.lang.String s) {
           super(s);
        }

        public SecureServiceStorageErrorException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceStorageErrorException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceStorageError msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceStorageError getFaultMessage(){
       return faultMessage;
    }
}
    