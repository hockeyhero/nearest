# escape=`

# Dockerfile to build hockeyhero_nearest image
# -------------------------------------------------
# To build the docker image, from a cmd.exe in the root of the repo
# run this command:  
#
# docker build -f Dockerfile -t app:hockeyhero_nearest .
#
# when done run docker images to list images. 

# Derived from 
FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=""

# src must be inside the context of the build. Either copy the jar file here
# first, or run this command from .\target
COPY /target/*.jar /app.jar

# Add docker-compose-wait tool -------------------
ENV WAIT_VERSION 2.7.3
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait

# The RUN instruction will execute any commands in a new layer on top of the current image 
# and commit the results. The resulting committed image will be used for the next step in 
# the Dockerfile.
RUN chmod +x /wait

# Provide defaults for the executing container, run app.jar
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar

# The container listens on port 8080 at runtime.
EXPOSE 8080



