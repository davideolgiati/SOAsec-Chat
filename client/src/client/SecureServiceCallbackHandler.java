
/**
 * SecureServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package client;

    /**
     *  SecureServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SecureServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SecureServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SecureServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for chatLogin method
            * override this method for handling normal response from chatLogin operation
            */
           public void receiveResultchatLogin(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from chatLogin operation
           */
            public void receiveErrorchatLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for chatLogout method
            * override this method for handling normal response from chatLogout operation
            */
           public void receiveResultchatLogout(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from chatLogout operation
           */
            public void receiveErrorchatLogout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sendMsg method
            * override this method for handling normal response from sendMsg operation
            */
           public void receiveResultsendMsg(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendMsg operation
           */
            public void receiveErrorsendMsg(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for reciveMsg method
            * override this method for handling normal response from reciveMsg operation
            */
           public void receiveResultreciveMsg(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reciveMsg operation
           */
            public void receiveErrorreciveMsg(java.lang.Exception e) {
            }
                


    }
    