package client;

import java.rmi.RemoteException;

// Apache
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
/*import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.rampart.policy.model.CryptoConfig;
import org.apache.rampart.policy.model.RampartConfig;
import org.apache.axiom.om.OMElement;

// IO
import java.io.File;
import java.io.InputStream;

// XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;*/

// W3C
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;

public class ChatAPI {
  // Costanti
  private static final String cfg = "/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml";
  // Variabili
  private SecureServiceStub stub;
  private String me;
  private String peer;

  ChatAPI(String user) {
    try {
      me = user;
      // ATTENZIONE TUTTI I PERCORSI SONO STATICI, SU UN'ALTRA MACCHINA NON GIRA!!!
      // Reading the xml configuration file
      /*
       * DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
       * DocumentBuilder b = f.newDocumentBuilder(); Document doc = b.parse(new
       * File(cfg));
       * 
       * //Changing the parameters XPath xPath =
       * XPathFactory.newInstance().newXPath(); String expr =
       * "/axisconfig/parameter[@name='OutflowSecurity']/action/user"; Node userNode =
       * (Node) xPath.compile(expr).evaluate(doc, XPathConstants.NODE);
       * userNode.setTextContent(me);
       * 
       * //Setting write options Transformer tf =
       * TransformerFactory.newInstance().newTransformer();
       * tf.setOutputProperty(OutputKeys.INDENT, "yes");
       * tf.setOutputProperty(OutputKeys.METHOD, "xml");
       * tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
       * 
       * //Writing changes to the document DOMSource domSource = new DOMSource(doc);
       * StreamResult sr = new StreamResult(new File(cfg)); tf.transform(domSource,
       * sr);
       */
      // To be able to load the client configuration from axis2.xml
      ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("axis-repo", cfg);
      stub = new SecureServiceStub(ctx, "http://localhost:8080/axis2/services/SecureService");
      ServiceClient sc = stub._getServiceClient();
      sc.engageModule("rampart");
      stub.doLogin(me);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void finalize() {
    try {
      stub.doLogout(me);
    } catch (RemoteException | SecureServiceClassNotFoundExceptionException | SecureServiceIOExceptionException
        | SecureServiceIllegalBlockSizeExceptionException | SecureServiceInvalidKeyExceptionException
        | SecureServiceBadPaddingExceptionException e) {
      e.printStackTrace();
    }
  }

  public void invia(String msg) {
    invia(msg, peer);
  }

  private void invia(String msg, String user) {
    try {
      stub.sendMsg(msg, user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String ricevi() {
    try {
      return stub.reciveMsg(me);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  public String getUser() {
    return me;
  }

  public String getPeer() {
    return peer;
  }

  public void setPeer(String user) {
    peer = user;
  }

  public String listaUtenti() throws SecureServiceClassNotFoundExceptionException, SecureServiceIOExceptionException,
      SecureServiceIllegalBlockSizeExceptionException, SecureServiceInvalidKeyExceptionException,
      SecureServiceBadPaddingExceptionException {
    String res;
    try {
      res = stub.userList();
    } catch (RemoteException | SecureServiceNullPointerExceptionException e) {
      e.printStackTrace();
      res = "";
    }
    if (getUser().equals(res) || "".equals(res)) {
      res = "Ancora nessun utente connesso ...";
    }
    return res;
  }

  public void request(String user) throws SecureServiceClassNotFoundExceptionException,
      SecureServiceIOExceptionException, SecureServiceSecureService_UserNotFoundExceptionException {
    try {
      stub.request(me, user);
    } catch (RemoteException | SecureServiceExceptionException e) {
      e.printStackTrace();
    }
  }

  public boolean accept(String user) {
    try {
      return stub.accept(me, user);
    } catch (RemoteException | SecureServiceClassNotFoundExceptionException | SecureServiceIOExceptionException
        | SecureServiceIllegalBlockSizeExceptionException | SecureServiceInvalidKeyExceptionException
        | SecureServiceBadPaddingExceptionException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deny(String user) {
    try {
      return stub.deny(me, user);
    } catch (RemoteException | SecureServiceClassNotFoundExceptionException | SecureServiceIOExceptionException | SecureServiceIllegalBlockSizeExceptionException | SecureServiceInvalidKeyExceptionException | SecureServiceBadPaddingExceptionException e) {
      e.printStackTrace();
      return false;
    }
  }

  public String[] checkForRequests() {
    try {
      return stub.checkForRequests(me);
    } catch (RemoteException | SecureServiceClassNotFoundExceptionException | SecureServiceIOExceptionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new String[0];
    }
  }

  public boolean checkForRequest(String user) {
    boolean res = false;
    String[] requests = checkForRequests();
    if (requests != null) {
      for (String _user : requests) {
        res = res || _user.equals(user);
      }
    }
    return res;
  }

  public boolean requestStatus(String myFriend) throws Exception {
    return stub.status(me, myFriend);
  }

  public boolean connectTo(String user) throws SecureServiceClassNotFoundExceptionException,
      SecureServiceIOExceptionException, SecureServiceSecureService_UserNotFoundExceptionException {
    boolean ret = false;
    if (checkForRequest(user)) {
      accept(user);
      return ret = true;
    } else {
      request(user);
      boolean keep = true;
      do {
        try {
          if (checkForRequest(user)) {
            accept(user);
            return ret = true;
          } else {
            ret = requestStatus(user);
          }
          keep = false;
        } catch (Exception e) {
          if (!"in attesa di risposta dall'utente".equals(e.getMessage())) {
            e.printStackTrace();
            keep = false;
            ret = false;
          }
        }
      } while (keep);
    }
    if (ret) {
      System.out.println("[i] connesso con " + user);
    } else {
      System.out.println("[x] connessione con " + user + "rifiutata");
    }
    return ret;
  }
}