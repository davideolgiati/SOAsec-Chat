
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
            * auto generated Axis2 call back method for doLogout method
            * override this method for handling normal response from doLogout operation
            */
           public void receiveResultdoLogout(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from doLogout operation
           */
            public void receiveErrordoLogout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for userList method
            * override this method for handling normal response from userList operation
            */
           public void receiveResultuserList(
                    java.lang.String result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from userList operation
           */
            public void receiveErroruserList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for doLogin method
            * override this method for handling normal response from doLogin operation
            */
           public void receiveResultdoLogin(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from doLogin operation
           */
            public void receiveErrordoLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deny method
            * override this method for handling normal response from deny operation
            */
           public void receiveResultdeny(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deny operation
           */
            public void receiveErrordeny(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for request method
            * override this method for handling normal response from request operation
            */
           public void receiveResultrequest(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from request operation
           */
            public void receiveErrorrequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for sendMsg method
            * override this method for handling normal response from sendMsg operation
            */
           public void receiveResultsendMsg(
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
                
           /**
            * auto generated Axis2 call back method for checkForRequests method
            * override this method for handling normal response from checkForRequests operation
            */
           public void receiveResultcheckForRequests(
                    java.lang.String[] result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkForRequests operation
           */
            public void receiveErrorcheckForRequests(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for accept method
            * override this method for handling normal response from accept operation
            */
           public void receiveResultaccept(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from accept operation
           */
            public void receiveErroraccept(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for status method
            * override this method for handling normal response from status operation
            */
           public void receiveResultstatus(
                    boolean result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from status operation
           */
            public void receiveErrorstatus(java.lang.Exception e) {
            }
                


    }
    