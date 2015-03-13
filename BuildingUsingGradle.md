# Introduction #

[Gradle](http://gradle.org) is an alternative build tool to [maven](http://maven.apache.org). This page captures the instructions to build the software using gradle.


# Details #

## Pre-requisites ##

  * Java
  * Gradle

## Building ##

  * Edit gradle.properties and update the location of your local maven repository.
  * Open the command prompt and run the following
gradle clean test war
  * Rename pls-generator-1.0-SNAPSHOT.war and deploy appropriately.