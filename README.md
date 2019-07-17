## About this
I've created this project in order to learn how to create a REST API using a 🎯 Clean Architecture approach using Kotlin. This is my first Kotlin project so don't trust too much in the solution. I'd appreciate you create an issue or send a PR if you see any improvement or posible bugs.

## Run
```
gradle run
```
This command starts the server, then you can access the API on your localhost.

#### Create a product
```sh
curl -X POST \
  http://localhost:8080/product \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "Favorite Jeans"
}'
```
Server logs:
```
ProductCreated(productId=ProductId(value=95156b7e-6c69-4aae-abf5-57d1ae2d141f), date=2019-07-17T15:49:12.584+02:00[Europe/Madrid])
```

#### Add a product to cart
```sh
curl -X POST \
  http://localhost:8080/cart \
  -H 'Content-Type: application/json' \
-d '{
	"userId": "1",
	"productId": "95156b7e-6c69-4aae-abf5-57d1ae2d141f"
}'
```
Server logs:
```
CartCreatedEvent(cartId=CartId(value=685d34fd-e109-4be5-b018-3a1f2bbf1abf), date=2019-07-17T15:52:17.973+02:00[Europe/Madrid])
ProductAddedToCart(cartId=CartId(value=685d34fd-e109-4be5-b018-3a1f2bbf1abf), date=2019-07-17T15:52:17.977+02:00[Europe/Madrid])
```
#### Get the user cart
```sh
curl 'http://localhost:8080/cart?userId=1' \
  -H 'Content-Type: application/json'

{"id":"685d34fd-e109-4be5-b018-3a1f2bbf1abf","products":[{"productId":"95156b7e-6c69-4aae-abf5-57d1ae2d141f","productName":"Favorite Jeans","quantity":1}]}
```

## Pending tasks
- Automated tests
- Dependency Injection framework?
- Autorization / Authentication
- Check coroutines and asynchrony
- Value objects validations
