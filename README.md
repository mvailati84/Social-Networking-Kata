# Social-Networking-Kata
A Kata based on the idea: https://monospacedmonologues.com/2013/04/the-social-networking-kata/

A console based social network where members can 
* post a message: "Alice -> I love this new social network"
* read a timeline of another member: "Bob"
* follow a member: "Alice follows Bob"
* see the wall of feeds: "Alice wall"

## Get started

Starting from the root folder of the project

1. compile the project
```sh
mvnw clean package
```
2. execute the application with:
```sh
java -jar .\target\social-networking-kata-0.0.1-SNAPSHOT.jar
```

### Example of usage
Below an example of usage of the network

```
Alice -> I love this new social network
Bob -> Having fun with my family 
Charlie -> Advice on where to eat good pizza 
Bob
Having fun with my family (59 seconds ago)
Alice follows Bob
Alice wall
Bob - Having fun with my family (1 minute ago)
Alice - I love this new social network (1 minute ago)
Alice follows Charlie
Alice wall
Charlie - Advice on where to eat good pizza (20 seconds ago)
Bob - Having fun with my family (1 minute ago)
Alice - I love this new social network (2 minutes ago)
exit
```
