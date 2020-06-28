#!/bin/bash
# Deploy AAR

cd service
jar -cvf SecureService.aar *
cp SecureService.aar /var/lib/tomcat8/webapps/axis2/WEB-INF/services
cd ..
