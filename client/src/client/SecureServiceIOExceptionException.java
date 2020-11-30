
/**
 * SecureServiceIOExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceIOExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1604476900139L;
    
    private client.SecureServiceStub.SecureServiceIOException faultMessage;

    
        public SecureServiceIOExceptionException() {
            super("SecureServiceIOExceptionException");
        }

        public SecureServiceIOExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceIOExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceIOExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceIOException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceIOException getFaultMessage(){
       return faultMessage;
    }
}
    