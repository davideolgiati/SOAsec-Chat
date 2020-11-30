
/**
 * SecureServiceInvalidKeyExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceInvalidKeyExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1606744555349L;
    
    private client.SecureServiceStub.SecureServiceInvalidKeyException faultMessage;

    
        public SecureServiceInvalidKeyExceptionException() {
            super("SecureServiceInvalidKeyExceptionException");
        }

        public SecureServiceInvalidKeyExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceInvalidKeyExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceInvalidKeyExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceInvalidKeyException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceInvalidKeyException getFaultMessage(){
       return faultMessage;
    }
}
    