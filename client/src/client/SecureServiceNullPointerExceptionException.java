
/**
 * SecureServiceNullPointerExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceNullPointerExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1604476900133L;
    
    private client.SecureServiceStub.SecureServiceNullPointerException faultMessage;

    
        public SecureServiceNullPointerExceptionException() {
            super("SecureServiceNullPointerExceptionException");
        }

        public SecureServiceNullPointerExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceNullPointerExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceNullPointerExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceNullPointerException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceNullPointerException getFaultMessage(){
       return faultMessage;
    }
}
    