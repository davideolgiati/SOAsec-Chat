package client;

import java.util.Properties;
import java.util.Scanner;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.rampart.policy.model.CryptoConfig;
import org.apache.rampart.policy.model.RampartConfig;

import org.apache.axiom.om.OMElement;

import java.io.File;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.xpath.*;

public class SecureServiceClient {
  protected static String username = "";
  protected static String password = "";

  public static void main(String[] args) throws Exception {
	
	Scanner keyboard = new Scanner(System.in);
	System.out.println("Inserisci il tuo nome utente:");
	username = keyboard.next();
	System.out.println("Inserisci la tua password:");
	password = keyboard.next();
	  
	// ATTENZIONE TUTTI I PERCORSI SONO STATICI, SU UN'ALTRA MACCHINA NON GIRA!!!
	// Reading the xml configuration file
	DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	DocumentBuilder b = f.newDocumentBuilder();
	Document doc = b.parse(new File("/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml"));

	//Changing the parameters
	XPath xPath = XPathFactory.newInstance().newXPath();
	String expr1 = "/axisconfig/parameter[@name='OutflowSecurity']/action/user";
	String expr2 = "/axisconfig/parameter[@name='OutflowSecurity']/action/encryptionUser";
	NodeList userNode = (NodeList) xPath.compile(expr1 + " | " + expr2).evaluate(doc, XPathConstants.NODESET);
	for (int i = 0; i < userNode.getLength(); i++) {
	    Node currentItem = userNode.item(i);
	    currentItem.setTextContent(username);
	}
	
	//Setting write options
	Transformer tf = TransformerFactory.newInstance().newTransformer();
	tf.setOutputProperty(OutputKeys.INDENT, "yes");
	tf.setOutputProperty(OutputKeys.METHOD, "xml");
	tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	
	//Writing changes to the document
	DOMSource domSource = new DOMSource(doc);
	StreamResult sr = new StreamResult(new File("/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml"));
	tf.transform(domSource, sr);
	
    // To be able to load the client configuration from axis2.xml
    ConfigurationContext ctx =
	ConfigurationContextFactory.createConfigurationContextFromFileSystem(
	    "axis-repo", "/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml");
    SecureServiceStub stub =
	new SecureServiceStub(ctx, "http://localhost:8080/axis2/services/SecureService");
    ServiceClient sc = stub._getServiceClient();
	sc.engageModule("rampart");

    stub.chatLogin(username);
    //String check = stub.reciveMsg(username);
    //String[] partials = message.split(" ");
    //if (partials[0] == "<request>") {
    //	stub.sendMsg(":match " + partials[1]);
    //} else {
    //	stub.sendMsg(":openConversation " + userToChatWith, username);
    //}
    //System.out.println(stub.reciveMsg("pippo"));
  }
}
