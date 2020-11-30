
/**
 * SecureServiceIllegalBlockSizeExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceIllegalBlockSizeExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1606744555316L;
    
    private client.SecureServiceStub.SecureServiceIllegalBlockSizeException faultMessage;

    
        public SecureServiceIllegalBlockSizeExceptionException() {
            super("SecureServiceIllegalBlockSizeExceptionException");
        }

        public SecureServiceIllegalBlockSizeExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceIllegalBlockSizeExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceIllegalBlockSizeExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceIllegalBlockSizeException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceIllegalBlockSizeException getFaultMessage(){
       return faultMessage;
    }
}
    