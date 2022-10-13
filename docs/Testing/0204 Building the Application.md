# Building the Application

To test Nearest you need to build the application. In a cmd.exe navigate to the repo folder (e.g. `C:\Git2\nearest`) and run the command: 

`mvn clean install -DskipTests `

You should observe the code builds cleanly. If you navigate to the `target` subfolder you should see a jar file. 

## Building the Docker Images for the Test  

In a cmd.exe navigate to the docker\mysql subfolder of the repo. If you edit the Dockerfile within you will see the command you need to run in order to build the MySQL docker image. In the cmd.exe run that command: 

`docker build -t mysql:hockeyhero_nearest .`  

You can confirm the image was built by running the docker command `docker images` to list the images.  

In the cmd.exe navigate to the docker\nearest subfolder of the repo. If you edit the Dockerfile within you will see the command you need to run in order to build the Nearest docker image.  Note you need to run this command <i>from the `target` subfolder</i> of the repo, so in the cmd.exe navigate to the `target` folder and run the command, making the necessary changes. In my case it was:  

`docker build -f C:\Git2\nearest\docker\nearest\Dockerfile -t app:hockeyhero_nearest .`  

Again you can confirm the image was built by running the docker command `docker images` to list the images. 

Finally, in the cmd.exe navigate to the docker subfolder of the repo.  If you edit the docker-compose.yml file within you will see the instructions you need to run in order to launch the system. In the cmd.exe run the command[^1][^2]: 

`docker-compose up`

You should see logging indicating both the nearest_mysql and nearest_app docker containers starting. 

[^1]: Docker now supports a `docker compose` command (no hyphen). </br>
[^2]: For automation testing add the `-d` (detach) flag so the workflow is not blocked. 


