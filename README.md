##Pre-installations:
* JDK-1.8
* Tomcat-7 or above
* Elastic search cluster version 2.4.1
* OS: Linux,Windows,Debian,Ubuntu,Solaris

##Search Driver API (GET)
http://tomcat-server:port/driver-tracker/public/drivers?latitude=28.57294&longitude=77.32601&radius=210&limit=1

##Save/Update API (PUT)
http://tomcat-server:port/driver-tracker/public/drivers/{id}/location

<br/>
Payload:
{ 
 "latitude":"18.99",
 "longitude":"56.89",
 "radius":"-10",
 "limit":"-10"  
 }
 
##WAR Installation:
* Make war file of project with maven tool i.e. mvn clean install -DskipITs
* Copy war file from /target folder of project and deploy on Tomcat server
* start or restart Tomcat

##Post instllaltion:
* configure '~/WEB-INF/classes/app-config.properties' file as per requirement
* change Elastic cluster setting at '~/WEB-INF/classes/elastic-beans.xml' file as per requirement
* configure messages '~/WEB-INF/classes/messages.properties' file as per requirement
* configure loggers '~/WEB-INF/classes/log4j.xml' file as per requirement



 
