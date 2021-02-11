# Backend-Linker
Backend for Linker Project

### Linker Project
LInk Collection to save & share links easily 

## Getting Started
You need:
* [MongoDB Database](https://www.mongodb.com/)

You need to define the uri in the application.properties to connect to your MongoDB database.
```
spring.data.mongodb.uri= here comes your uri
```
Secondly you need to set a pepper for your password hashing
```
security.pepper= here comes your pepper (at least 16 Characters)
```
Then you need to at a signature for the jwt. It needed to be encoded in base64
```
security.signature= here comes your signature
```

## Authors
* **Linus Ackermann**
