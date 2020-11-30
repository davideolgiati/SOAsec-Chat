
/**
 * SecureServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1604481862221L;
    
    private client.SecureServiceStub.SecureServiceException faultMessage;

    
        public SecureServiceExceptionException() {
            super("SecureServiceExceptionException");
        }

        public SecureServiceExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceException getFaultMessage(){
       return faultMessage;
    }
}
    