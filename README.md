"# tests" 
## What is all about?

1. Spring cloud
2. Spring boot microservices
   1. Config Server.
   2. Eureka. 
   3. Microservices
      1. Guest Service
      2. Reservation Service
      3. Room Reservation Service
      4. Room Service

## Running docker

This will create an instance of:
1. Kafka
2. Zookeeper
3. Prometheus
4. Grafana

```
    cd docker-dependencies
    docker-compose up -d
```

## Running Config Server.

Config server needs a `GitHub` repository in order to run. If you want to try locally:

```
    cd git-config
    git init
```

In the Java properties replace this:

```
    spring.cloud.config.server.git.uri=file:///Users/your/folder/name
```

## Running locally

Once Docker is running, just run every main class from the project. 

Remember to set up you Java version.
