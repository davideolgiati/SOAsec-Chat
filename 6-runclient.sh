#!/bin/bash
# Run Client

cd client/src
java -Djava.ext.dirs=../../lib client.SecureServiceClient -Djavax.net.ssl.trustStore="$JAVA_HOME/jre/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword="password" -Djavax.net.ssl.trustStoreType="JKS"
cd ../..
