		 SOA SECURITY LAB
		
** Software Installation

To setup the environment for the lab you need to install the included packages. To avoid problems with system paths, we suggest to install everything in a new folder under root (C:\), for example C:\SOASec. You only need to have a fully working java JDK.

Remember that with Windows you must give to the User full control on the folder and subfolders, since we are going to write and move file in it. 
To do so, right click on SOASec folder --> Properties --> Security. Click on "Users" and Modify. Select "Full Control" and click OK.

Verify that everything is working running the file 1-startTomcat.bat

PLEASE NOTE: you need to run the console as Administrator (Windows)

To execute the script, you must configure the following environment variables (Control Panel --> System --> Advanced System configuration --> Environment Variables). Insert new system variables (or update old variables if already present):

- CATALINA_HOME -> Tomcat installation folder (e.g., C:\SOASec\apache-tomcat-7.0.39)
- JAVA_HOME -> Java JDK installation folder (e.g., C:\Program Files\Java\jdk1.7.0_45)
- AXIS2_HOME -> AXIS2 standalone binary distribution installation folder (e.g., C:\SOASec\axis2-1.6.2)
- PATH-> append the following directories to the path, if not already present: [AXIS2_HOME]\bin;[JAVA_HOME]\bin

Before starting the test, you need a first run of Tomcat to deploy AXIS2 WAR. Please verify that you do not have any other tool using the 8080 port (netstat gives you information on all the actual open ports).

WARNING: SHELL scripts for Linux have not been tested. Please verify that they work correctly.