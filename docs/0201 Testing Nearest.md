# Testing Nearest  

Its possible to test the Nearest service to prove its working.  This also helps prove your environment.  
I'm going to include screen shots from my workstation. For context my Git Repo is here: 

`C:\Git2\nearest`

But yours can be anywhere.  

## Building the Application

In a cmd.exe navigate to the repo folder (e.g. `C:\Git2\nearest`) and run the command: 

`mvn compile`

You should observe the code builds cleanly.  

## Building the Docker Images for the Test  

In a cmd.exe navigate to the docker\mysql subfolder of the repo. If you edit the Dockerfile within you will see the command you need to run in order to build the MySQL docker image. In the cmd.exe run the command: 

`docker build -t mysql:hockeyhero_nearest .`  

You can confirm the image was built by running the docker command `docker images` to list the images.  

In the cmd.exe navigate to the docker\nearest subfolder of the repo. If you edit the Dockerfile within you will see the command you need to run in order to build the Nearest docker image.  Note you need to run this command from the target subfolder of the repo, so in the cmd.exe navigate there and run the command. In my case it was:  

`docker build -f C:\Git2\nearest\docker\nearest\Dockerfile -t app:hockeyhero_nearest .`  

Again you can confirm the image was built by running the docker command `docker images` to list the images. 

Finally, in the cmd.exe navigate to the docker subfolder of the repo.  If you edit the docker-compose.yml file within you will see the instructions you need to run in order to launch the system. In the cmd.exe run the command: 

`docker-compose up`

You should see logging indicating both the nearest_mysql and nearest_app docker containers starting. 

## Testing Using Postman

With the docker images running you can test the system using Postman. 

