# Architecture and Design

I confess I wrote Nearest a few years back, its had several incarnations, but I've settled on the architecture so let's walk through it. 
For background, the solution consists of a `hockeyhero_nearest` war (web service) connect to a MySQL database. 
The REST interface contains simple CRUD + Find operations. When you create an entry to give it a coordinate (lat, long). From there you can then search
for entries withina  range of a location.  


I assume you've forked the repository and have download to your machine a clone. 

## Root Folder (.)

In the root folder are two files - dec.cmd and enc.cmd - they are responsible for decrypting and encrypting the application.properties files respectively.
The properties file is stored in source control encrypted. Secrets baked into the CI/CD mean the file can be decrypted on the build server when building. 
For desktop development, you will need to edit the application.properties.empty file, add a db username/password and save as application.properties. 

From the root folder you should be able to execute in a cmd.exe `mvn compile` and build the project. 


## ./.circleci

Initially this repo was build using CircleCI. The build is being ported to GitHub actions making this folder obsolete. 

## ./.github

The build of this repo is migrating to GitHub Actions. This folder contain Yaml Template files and build yaml files. 

## ./.mvn

TBD. 

## ./docker

The docker folder 

## ./docs

## ./openssl-1.1.1

## ./src

## ./target



