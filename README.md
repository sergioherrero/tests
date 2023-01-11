## What is all about?

1. Spring cloud
2. Spring boot microservices
   1. Config Server
   2. Eureka
   3. Microservices
      1. Guest Service              
      2. Reservation Service       
      3. Room Reservation Service   
      4. Room Service               

## Port list:

| Service                  | Ports  | 
|--------------------------|--------|
| Config Server            |  8888  |
| Eureka                   |  8761  |
| Guest Service            |  8081  |
| Reservation Service      |  8082  |
| Room Reservation Service |  8080  |
| Room Service             |  8083  |
| Kafka                    |  9092  |
| Zookeeper                |  2181  |
| Prometheus               |  9090  |
| Grafana                  |  3000  |

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

Github configuration:

```
    https://github.com/sergioherrero/tests-configurations
```

## Running locally

Once Docker is running, just run every main class from the project. 

Remember to set up you Java version.
