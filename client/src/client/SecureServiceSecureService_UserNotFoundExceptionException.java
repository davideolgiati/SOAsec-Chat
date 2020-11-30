
/**
 * SecureServiceSecureService_UserNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceSecureService_UserNotFoundExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1605349000329L;
    
    private client.SecureServiceStub.SecureServiceSecureService_UserNotFoundException faultMessage;

    
        public SecureServiceSecureService_UserNotFoundExceptionException() {
            super("SecureServiceSecureService_UserNotFoundExceptionException");
        }

        public SecureServiceSecureService_UserNotFoundExceptionException(java.lang.String s) {
           super(s);
        }

        public SecureServiceSecureService_UserNotFoundExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceSecureService_UserNotFoundExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceSecureService_UserNotFoundException msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceSecureService_UserNotFoundException getFaultMessage(){
       return faultMessage;
    }
}
    