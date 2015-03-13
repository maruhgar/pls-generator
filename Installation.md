# Introduction #

This is a web application, which can be deployed in any servlet container (verified on Tomcat 6).


# Details #

Currently, this project is only available in source format.

Pre-requisites:

  1. Java :  This application works with Java 6.  Download and install the latest version of JDK 6.
  1. Maven : This is a maven project. Ensure maven is setup correctly on your system
  1. Any app server:  Project has been verified on Tomcat 6.

Steps to install

  1. Download the source following the instructions in [source](http://code.google.com/p/pls-generator/source/checkout).
  1. Open a command prompt and go to the folder containing pom.xml
  1. Run mvn install
  1. Look for pls-1.0-SNAPSHOT.war created in target folder
  1. Rename this war file appropriately and drop it in the web application folder of your app server.
  1. Open the application on your browser. You should be good to go