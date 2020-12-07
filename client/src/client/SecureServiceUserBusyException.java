
/**
 * SecureServiceUserBusyException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package client;

public class SecureServiceUserBusyException extends java.lang.Exception{

    private static final long serialVersionUID = 1607200261363L;
    
    private client.SecureServiceStub.SecureServiceUserBusy faultMessage;

    
        public SecureServiceUserBusyException() {
            super("SecureServiceUserBusyException");
        }

        public SecureServiceUserBusyException(java.lang.String s) {
           super(s);
        }

        public SecureServiceUserBusyException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public SecureServiceUserBusyException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(client.SecureServiceStub.SecureServiceUserBusy msg){
       faultMessage = msg;
    }
    
    public client.SecureServiceStub.SecureServiceUserBusy getFaultMessage(){
       return faultMessage;
    }
}
    