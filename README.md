# Howzat API

API server for Howzat

### Setting up Howzat database using Docker

Install [Docker](https://docs.docker.com/desktop/#download-and-install) and run the following command.
``` 
docker run --name howzat -e POSTGRES_PASSWORD=password -e POSTGRES_DB=howzatdb -d -p 5432:5432 postgres:alpine 
```
Flyway will take care of adding neccessary tables into the database when the server is started.