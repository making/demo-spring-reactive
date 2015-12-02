# Spring Reactive on Spring Boot Demo

This demo uses MongoDB.

    $ docker run --name mongo -p 27017:27017 -d mongo

POST a message

    $ curl -v -X POST -H 'Content-Type: application/json' -H 'Accept: application/json'  -d '{"text":"hello"}' http://localhost:8080/message
    > POST /message HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.44.0
    > Content-Type: application/json
    > Accept: application/json
    > Content-Length: 16
    > 
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Content-Length: 0
    < Date: Wed, 02 Dec 2015 17:40:50 GMT
    < 
    $ curl -v -X POST -H 'Content-Type: application/json' -H 'Accept: application/json'  -d '{"text":"demo"}' http://localhost:8080/message
    > POST /message HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.44.0
    > Content-Type: application/json
    > Accept: application/json
    > Content-Length: 15
    > 
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Content-Length: 0
    < Date: Wed, 02 Dec 2015 17:41:53 GMT
    < 

GET messages

    $ curl -v -X GET -H 'Accept: application/json' localhost:8080/message
    > GET /message HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.44.0
    > Accept: application/json
    > 
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Content-Length: 35
    < Date: Wed, 02 Dec 2015 17:42:17 GMT
    < 
    [{"text":"hello"},{"text":"demo"}]]