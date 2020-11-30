
/**
 * SecureServiceBadPaddingExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceBadPaddingExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1606744555393L;
    
    private client.SecureServiceStub.SecureServiceBadPaddingException faultMessage;

    
        public SecureServiceBadPaddingExceptionException() {
            super("SecureServiceBadPaddingExceptionException");
        }

        public SecureServiceBadPaddingExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceBadPaddingExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceBadPaddingExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceBadPaddingException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceBadPaddingException getFaultMessage(){
       return faultMessage;
    }
}
    