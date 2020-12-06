package client;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

// Apache
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.neethi.PolicyEngine;
import org.apache.rampart.policy.model.CryptoConfig;
import org.apache.rampart.policy.model.RampartConfig;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.client.Options;
import org.apache.rampart.RampartMessageData;

// IO
import java.io.File;
import java.io.InputStream;

// XML
import javax.xml.stream.XMLStreamException;
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
import org.xml.sax.SAXException;

// W3C
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ChatAPI {
  // Costanti
  private static final String cfg = "/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml";
  // Variabili
  private SecureServiceStub stub;
  private String me;
  private String peer;

  private void handleException(SecureServiceClassNotFoundExceptionException e, String metodo) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String ex = sw.toString();
    handleException(metodo
        + " : ClassNotFoundException, Classe di eccezione relativa alla serializzazione. La classe che si sta tentando di serializzare/deserializzare non appartiene a quelle note",
        ex);
  }

  private void handleException(SecureServiceIOExceptionException e, String metodo) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String ex = sw.toString();
    handleException(metodo
        + " : IOException, Classe di eccezione relativa alla serializzazione. Errore di tipo I/O durante la lettura/scrittura della classe da/sullo storage",
        ex);
  }

  private void handleException(RemoteException e, String metodo) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String ex = sw.toString();
    handleException(metodo + " : RemoteException, Classe di eccezione remota generica. Non ho altre informazioni", ex);
  }

  private void handleException(SecureServiceExceptionException e, String metodo) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String ex = sw.toString();
    handleException(metodo + " : Exception, Eccezione generica lato server. Non ho altre informazioni", ex);
  }

  private void handleException(SecureServiceSecureService_UserNotFoundExceptionException e, String metodo) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    String ex = sw.toString();
    handleException(
        metodo + " : UserNotFoundException, Classe di eccezione personalizzata. L'utente desiderato non esiste", ex);
  }

  private void handleException(String msg, String trace) {
    System.out.println(msg);
    System.out.println("Mostare StackTrace? [s/n]");
    Scanner keyboard = new Scanner(System.in);
    char res = keyboard.next().toLowerCase().charAt(0);
    if (res == '\0' || res == 's') {
      System.out.println(trace);
    }
    keyboard.close();
    System.exit(1);
  }

  ChatAPI(String user) {
    try {
      me = user;
      // Reading the xml configuration file
      DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
      DocumentBuilder b = f.newDocumentBuilder();
      Document doc = b.parse(new File(cfg));

      // Changing the parameters
      XPath xPath = XPathFactory.newInstance().newXPath();
      String expr = "/axisconfig/parameter[@name='OutflowSecurity']/action/user";
      Node userNode = (Node) xPath.compile(expr).evaluate(doc, XPathConstants.NODE);
      userNode.setTextContent(me);

      // Setting write options
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty(OutputKeys.INDENT, "yes");
      tf.setOutputProperty(OutputKeys.METHOD, "xml");
      tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

      // Writing changes to the document
      DOMSource domSource = new DOMSource(doc);
      StreamResult sr = new StreamResult(new File(cfg));
      tf.transform(domSource, sr);

      // To be able to load the client configuration from axis2.xml
      ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("axis-repo", cfg);
      stub = new SecureServiceStub(ctx, "https://localhost:8443/axis2/services/SecureService");
      ServiceClient sc = stub._getServiceClient();

      // Loading policy
      Options options = sc.getOptions();
      options.setProperty(RampartMessageData.KEY_RAMPART_POLICY, loadPolicy("https-policy.xml"));

      sc.engageModule("rampart");
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    try {
      stub.doLogin(me);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
  }

  // Load policy file from classpath.
  private static Policy loadPolicy(String name) throws XMLStreamException {
    InputStream resource = ChatAPI.class.getResourceAsStream(name);
    StAXOMBuilder builder = new StAXOMBuilder(resource);
    return PolicyEngine.getPolicy(builder.getDocumentElement());
  }

  @Override
  public void finalize() {
    try {
      stub.doLogout(me);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
  }

  public void invia(String msg) {
    invia(msg, peer);
  }

  private void invia(String msg, String user) {
    try {
      stub.sendMsg(msg, user);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
  }

  public String ricevi()
      throws RemoteException, SecureServiceClassNotFoundExceptionException, SecureServiceIOExceptionException, SecureServiceSecureService_UserNotFoundExceptionException {
    String res = "";
    res = stub.reciveMsg(me);
    return res;
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

  public String listaUtenti() {
    String res;
    try {
      res = stub.userList(getUser());
    } catch (Exception e) {
      e.printStackTrace();
      res = "";
    }
    if (getUser().equals(res) || "".equals(res)) {
      res = "Ancora nessun utente connesso ...";
    }
    return res;
  }

  public void request(String user) {
    try {
      stub.request(me, user);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
  }

  public boolean accept(String user1, String user2) {
    boolean res = false;
    try {
      res = stub.accept(user1, user2);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
    return res;
  }

  public boolean deny(String user) {
    boolean res = false;
    try {
      res = stub.deny(me, user);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
    return res;
  }

  public String[] checkForRequests() {
    String[] res = {};
    try {
      res = stub.checkForRequests(me);
    } catch (RemoteException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceClassNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceIOExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    } catch (SecureServiceSecureService_UserNotFoundExceptionException e) {
      StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
      handleException(e, stackTraceElements[0].getMethodName());
    }
    return res;
  }

  public boolean checkForRequest(String user) {
    boolean res = false;
    String[] requests = checkForRequests();
    if (requests != null) {
      for (String _user : requests) {
        if (_user.equals(user)) {
          res = true;
        } else {
          deny(_user);
        }
      }
    }
    return res;
  }

  public boolean requestStatus(String myFriend) 
      throws RemoteException, SecureServiceExceptionException, SecureServiceSecureService_UserNotFoundExceptionException, SecureServiceClassNotFoundExceptionException, SecureServiceIOExceptionException{
    return stub.status(me, myFriend);
  }

  public boolean connectTo(String user) {
    boolean ret = false;
    boolean first = true;
    if (checkForRequest(user)) {
      accept(me, user);
      ret = true;
    } else {
      request(user);
      boolean keep = true;
      do {
        try {
          if (checkForRequest(user)) {
            accept(me, user);
            accept(user, me);
            ret = true;
          } else {
            ret = requestStatus(user);
          }
          keep = false;
        } catch (Exception e) {
          if (!"in attesa di risposta dall'utente".equals(e.getMessage())) {
            e.printStackTrace();
            keep = false;
            ret = false;
          } else {
            if (first) {
              System.out.println(e.getMessage());
              first = false;
            } else {
              System.out.print('.');
            }
            try {
              Thread.sleep(1000);
            } catch (InterruptedException ex) {
              // TODO Auto-generated catch block
              ex.printStackTrace();
            }
          }
        }
      } while (keep);
    }
    if (!first) {
      System.out.print('\n');
    }
    if (ret) {
      System.out.println("[i] connesso con " + user);
      peer = user;
    } else {
      System.out.println("[x] connessione con " + user + "rifiutata");
    }
    return ret;
  }
}
