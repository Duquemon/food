First at all, you need to install docker and database postgres, then run this command in terminal 
when you finalize installation of docker, run this command for postgres 
I using port 5433 to run the microservices, but you can change if you want port of projects on all of projects in the application.yml. 
docker run --name postgres-duque -p 5433:5432 -e POSTGRES_PASSWORD=postgres -d postgres:14.0

`infrastructure/docker-compose `

1. `docker-compose -f common.yml -f zookeeper.yml up `
 
2. `docker-compose -f common.yml -f kafka_cluster.yml up `

3. `docker-compose -f common.yml -f init_kafka.yml up`

### Make sure to generate the topic from localhost:9000

#Then compile the projects from root folder with 
`mvn clean install`

Run the project and you can test the project with this endpoints: 

1. Create customer: 

method ***POST***
```http://localhost:8184/customers```

#### Body 
```json
{
  "customerId": "d215b5f8-0249-4dc5-89a3-51fd148cfb41",
  "username": "username",
  "firstName": "firstname",
  "lastName": "lastname",
  "scoring": 90
}
```
2. Create Orders: 

method ***POST*** 
`http://localhost:8181/orders`

#### Body 
```json
{
  "customerId": "6c5f98cc-e439-4c4d-bb6d-453394d3804b",
  "restaurantId": "d215b5f8-0249-4dc5-89a3-51fd148cfb45",
  "address": {
    "street": "siempre viva",
    "postalCode": "28012",
    "city": "santiago"
  },
  "price": 50.00,
  "items": [
    {
      "productId": "d215b5f8-0249-4dc5-89a3-51fd148cfb48",
      "quantity": 1,
      "price": 50.00,
      "subTotal": 50.00
    }
  ]
}
```

3. List all orders: 
method ***GET:***
 `http://localhost:8181/orders`

#### Return 
```json
[
    {
        "orderTrackingId": "86429bf0-15a0-48bf-bd14-bdc47b6e82a7",
        "orderStatus": "APPROVED",
        "failureMessages": []
    },
    {
        "orderTrackingId": "b11f999b-2129-4a2d-9e2f-fd9958a2ba2b",
        "orderStatus": "APPROVED",
        "failureMessages": []
    },
    {
        "orderTrackingId": "98082e6e-f8d5-4bbf-9d19-e0a7f5bf7847",
        "orderStatus": "APPROVED",
        "failureMessages": []
    }
]
```

Check by track id: 
http://localhost:8181/orders/${id}


