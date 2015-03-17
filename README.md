# pls-generator Playlist Generator

## Introduction

This web application provides an interface for users to browse media folders and create playlists, which can then be passed to [Shoutcast](http://www.shoutcast.com/broadcastnow) server to be played.

This allows multiple users to share music in the intranet (or even internet) subject to necessary copyrights.

This is based on (to be precise, enhancement) to a similar application [shoutcast-jukebox](http://sourceforge.net/projects/shoutcastpls/) created by my friend and ex-colleague Bosky.

## Pre-requisites

* Java 8
* Apache maven 3.2.5
* Apache tomcat or jetty (with jsp support) [optional, if you use embedded tomcat]
* Shoutcast music server

## Installation (embedded tomcat)

* Clone the repository
* Run the following:

`mvn -P tomcat clean package`

* Go to `target` subfolder and run

`java -jar pls-1.2-SNAPSHOT-war-exec.jar`

## Manual Installation

* Clone the repository
* Run the following:

`mvn clean package`

* Copy over `pls-1.2.-SNAPSHOT.war` to the deployment folder of your app server

## Using the app

* Browse to the app 
* Click on `Configure` link
* Specify the authentication details.  (Default username: admin and Password: test1234)
* Configure the following values
  * Media folder
  * Shoutcast url
  * Number of days since creation that a song/folder should show up "New"
* Restart the app server (if required) to reflect the changes
* Browse again to the app
* Choose the files/folder desired
* Click on `Play` and your Winamp should open with the dynamically created playlist

