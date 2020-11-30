
/**
 * SecureServiceClassNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceClassNotFoundExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1604476900124L;
    
    private client.SecureServiceStub.SecureServiceClassNotFoundException faultMessage;

    
        public SecureServiceClassNotFoundExceptionException() {
            super("SecureServiceClassNotFoundExceptionException");
        }

        public SecureServiceClassNotFoundExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceClassNotFoundExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceClassNotFoundExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceClassNotFoundException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceClassNotFoundException getFaultMessage(){
       return faultMessage;
    }
}
    