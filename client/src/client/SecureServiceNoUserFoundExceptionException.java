
/**
 * SecureServiceNoUserFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceNoUserFoundExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1594367577989L;
    
    private client.SecureServiceStub.SecureServiceNoUserFoundException faultMessage;

    
        public SecureServiceNoUserFoundExceptionException() {
            super("SecureServiceNoUserFoundExceptionException");
        }

        public SecureServiceNoUserFoundExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceNoUserFoundExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceNoUserFoundExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceNoUserFoundException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceNoUserFoundException getFaultMessage(){
       return faultMessage;
    }
}
    