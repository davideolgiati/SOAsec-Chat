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

public class SecureServiceClient {
	
	// -uri http://localhost:8080/axis2/services/SecureService?wsdl -p tutorial.rampart.client -uw
	
	public static void main(String[] args) throws Exception {
		
    	//To be able to load the client configuration from axis2.xml
	//ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("axis-repo",null);
    	ConfigurationContext ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem("axis-repo", "/home/soasec/final/client/src/axis-repo/conf/axis2.xml");
		
		SecureServiceStub stub = new SecureServiceStub(ctx,"http://localhost:8080/axis2/services/SecureService");
		
		ServiceClient sc = stub._getServiceClient();
		
        	sc.engageModule("rampart");
	
		int a = 3;
		int b = 4;
		
		int result = stub.add(a, b);
		
		System.out.println(a + " + " + b + " = " + result);
		
	}
	


}
