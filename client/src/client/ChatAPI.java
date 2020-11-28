package client;

// Utils
import java.util.Properties;
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
  private String username;
  private String peer;

  ChatAPI(String user){
    try {
      username = user;
      // ATTENZIONE TUTTI I PERCORSI SONO STATICI, SU UN'ALTRA MACCHINA NON GIRA!!!

      // Reading the xml configuration file
      DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
      DocumentBuilder b = f.newDocumentBuilder();
      Document doc = b.parse(new File(cfg));

      //Changing the parameters
      XPath xPath = XPathFactory.newInstance().newXPath();
      String expr = "/axisconfig/parameter[@name='OutflowSecurity']/action/user";
      Node userNode = (Node) xPath.compile(expr).evaluate(doc, XPathConstants.NODE);
      userNode.setTextContent(username);

      //Setting write options
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty(OutputKeys.INDENT, "yes");
      tf.setOutputProperty(OutputKeys.METHOD, "xml");
      tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

      //Writing changes to the document
      DOMSource domSource = new DOMSource(doc);
      StreamResult sr = new StreamResult(new File(cfg));
      tf.transform(domSource, sr);

      // To be able to load the client configuration from axis2.xml
      ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("axis-repo", cfg);
      stub = new SecureServiceStub(ctx, "https://localhost:8443/axis2/services/SecureService");
      ServiceClient sc = stub._getServiceClient();

      //Loading policy
      Options options = sc.getOptions();
      options.setProperty(RampartMessageData.KEY_RAMPART_POLICY, loadPolicy("policy.xml"));

      //Engaging Rampart
      sc.engageModule("rampart");

      stub.chatLogin(username);
    } catch (Exception e) {
      System.out.println("E' successo qualcosa, non lo so");
	    e.printStackTrace();
    }
  }

  // Load policy file from classpath.
  private static Policy loadPolicy(String name) throws XMLStreamException {
    InputStream resource = ChatAPI.class.getResourceAsStream(name);
    StAXOMBuilder builder = new StAXOMBuilder(resource);
    return PolicyEngine.getPolicy(builder.getDocumentElement());
  }

  private String toMe(String msg) {
    try {
      return stub.sendMsg(msg, username);
    } catch (Exception e) {
      System.out.println("toMe(String msg) error\n" + e.toString());
      return "error";
    }
  }

  public String getPeer(){
    return peer;
  }

  public String invia(String msg, String peer) {
    try {
      return stub.sendMsg(msg, peer);
    } catch (Exception e) {
      System.out.println("invia(String msg) error");
      return "error";
    }
  }

  public String invia(String msg) {
    try {
      return stub.sendMsg(msg, peer);
    } catch (Exception e) {
      System.out.println("invia(String msg) error");
      return "error";
    }
  }

  public String ricevi() {
    try {
      return stub.reciveMsg(username);
    } catch (Exception e) {
      System.out.println("ricevi() error");
      return "error";
    }
  }

  public String listaUtenti() {
    toMe(":listUser");
    String res = ricevi();
    if ("".equals(res)){
      res = "Ancora nessun utente connesso ...";
    }
    return res;
  }

  public boolean connectTo(String utente) {
    try {
    peer = utente;
    toMe(":openConversation " + utente);
    System.out.println("Mi sto connettendo a " + utente + " ...");
    String res = ricevi();
    // devi aggiungere un timeout
    boolean keep = true;
    boolean ret = true;
    String text = "";
    while (keep) {
      while ("".equals(res)){
	res = ricevi();
      }
      String[] info = res.split(" ");
      if (info[0] == "<open>"){
	// apri convo
	if (info[1] == utente) {
	  text = "Connesso con ";
	  text += utente;
	  System.out.println(text);
	} else {
	  // siamo VIP
	  text = "Ricevuta richiesta da " + info[1] + "\n";
	  text += "Continuare tentetaivo con [ A ] " + utente + " o Connetersi con [ B ]" + info[1] + " ?\n";
	  text += "[A / B] ?\n";
	  System.out.println(text);
	  // fare input
	  Scanner keyboard = new Scanner(System.in);
	  String answer = keyboard.next();
	  answer = answer.toLowerCase();
	  while ((!"a".equals(answer.charAt(0))) || (!"b".equals(answer.charAt(0)))) {
	    text = "Scegliere tra le due opzioni [A / B]\n";
	    text += "[ A ] " + utente + "\n";
	    text += "[ B ] " + info[1] + "\n";
	    System.out.println(text);
	    answer = keyboard.next();
	    answer = answer.toLowerCase();
	  }
	  if ("b".equals(answer.charAt(0))) {
	    keep = false;
	    invia("<close> " + username);
	    peer = info[1];
	    invia("<open> " + username);
	    text = "Connesso con " + peer;
	  } else {
	    String tmp = peer;
	    peer = info[1];
	    invia("<close> " + username);
	    peer = tmp;
	    text = "Attendo risposta da " + peer;
	    System.out.println(text);
	  }
	}
      } else if (info[0] == "<close>"){
	// no convo
	text = "Connessione con " + utente + " rifiutata";
	System.out.println(text);
	ret = false;
	keep = false;
      } else {
	// che cazz succ
      }
    }
    return ret;
    } catch (Exception e) {
      return false;
    }
  }
}
