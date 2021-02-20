# Development Environment

The following details the minimum set of tools required to develop for HockeyHero. All of them are free. This document will walk through the different tools you need and at the end will walk you through a simple test. 

I do all my development on a desktop running Win10 so these instructions are Win10 centric. You will have to make the necessary changes if you run on a different platform. 

## Code

You need an editor. The primary language is Java using the Spring Framework. If you have a favorite editor, use it. Because I've spent my entire career on the commercial side of software developing Windows Desktop applications I lean towards Visual Studio. More recently in the office we are switching to [VSCode](https://code.visualstudio.com/).  

## Node

You need [Node.js](https://nodejs.org/en/) installed on your machine.  To check if you have node installed, at a cmd.exe type:

`node --version`

If its installed you will get a version (mine is v12.18.0). If necessary visit the link then download and install the LTS (Long Term Support) version. At the time of writing its v14.15.5. 

## Java

You need the [Java JDK](https://www.java.com/en/download/help/download_options.html) installed on your machine. To check if you have it already, at a cmd.exe type: 

`java -version`

If its installed you will get a version

## Maven

You need Maven installed on your machine. To check if you have it already, at a cmd.exe type: 

`mvn --version`

If its installed you will get a version. 

## GitHub and Git

You will need a personal GitHub account. 

You need Git installed on your machine. 

### GitHub Desktop

TBD

### Beyond Compare

May not be required. There's a difftool in GitHub. But its good to use it locally. 

### Text Editor

Atom, Notepad++

## Docker

You need docker installed on your machine. 

## Postman

You need Postman installed on your machine. 

# Testing the Environment

You have all the tools installed, but does it work? 