#!/bin/bash
# Run Client

cd client/src
java -Djava.ext.dirs=../../lib client.SecureServiceClient
cd ../..