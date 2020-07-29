package client;

import java.util.Properties;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.PolicyInclude;
import org.apache.neethi.Policy;
import org.apache.rampart.policy.model.CryptoConfig;
import org.apache.rampart.policy.model.RampartConfig;

import org.apache.axiom.om.OMElement;

import org.apache.axis2.client.Options;

public class SecureServiceClient {
  // -uri http://localhost:8080/axis2/services/SecureService?wsdl -p tutorial.rampart.client -uw

  protected static String username = "";
  protected static String password = "";

  public static void main(String[] args) throws Exception {
    // To be able to load the client configuration from axis2.xml
    // ATTENZIONE IL PERCORSO E' STATICO, SU UN'ALTRA MACCHINA NON GIRA!!!
    ConfigurationContext ctx =
	ConfigurationContextFactory.createConfigurationContextFromFileSystem(
	    "axis-repo", "/home/soasec/SOAsec-Chat/client/src/axis-repo/conf/axis2.xml");
    SecureServiceStub stub =
	new SecureServiceStub(ctx, "http://localhost:8080/axis2/services/SecureService");
    ServiceClient sc = stub._getServiceClient();
	/*
	NON TOCCARE!!!
	Options options = sc.getOptions();
    	options.setProperty(RampartMessageData.KEY_RAMPART_POLICY, loadPolicy("policy.xml"));
    	options.setUserName("client1");
    	options.setPassword("password1");
	*/
	sc.engageModule("rampart");

    username = "client1";
    password = "password1";

    stub.sendMsg(":chatLogin", "davide");
    stub.sendMsg(":chatLogin", "andrea");
    stub.sendMsg(":chatLogin", "pippo");
    stub.sendMsg(":listUsers", "pippo");
    System.out.println(stub.reciveMsg("pippo"));
  }
}
