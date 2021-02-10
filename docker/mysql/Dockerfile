# escape=`

# Dockerfile to build mysql image
# To build - from a cmd.exe execute the following: 
# -------------------------------------------------
# docker build -t mysql:hockeyhero_nearest .

# Derived from official mysql image (our base image)
FROM mysql:8.0

# Add a database
ENV MYSQL_DATABASE hockeyhero_nearest

# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup. This will load the test data and 
# run some isolated tests
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/

