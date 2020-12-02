#!/bin/bash
# Create Stub
export JAVA_HOME
export AXIS2_HOME
export PATH

rm -f client/src/client/SecureServiceStub*.*
rm -f client/src/client/SecureServiceCallbackHandler*.*
/home/soasec//axis2-1.6.2/bin/wsdl2java.sh -uri https://localhost:8443/axis2/services/SecureService?wsdl -uw -p client -o client/
