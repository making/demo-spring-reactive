# Spring Reactive on Spring Boot Demo

This demo uses MongoDB.

    $ docker run --name mongo -p 27017:27017 -d mongo

## POST a message

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

## GET messages

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
    
## POST messages

Same API can receive a JSON array as a stream.

    $ curl -v -X POST -H 'Content-Type: application/json' -H 'Accept: application/json'  -d '[{"text":"msg1"},{"text":"msg2"},{"text":"msg3"},{"text":"msg4"},{"text":"msg5"},{"text":"msg6"},{"text":"msg7"},{"text":"msg8"},{"text":"msg9"},{"text":"msg10"}]' http://localhost:8080/message
    > POST /message HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.44.0
    > Content-Type: application/json
    > Accept: application/json
    > Content-Length: 162
    > 
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Content-Length: 0
    < Date: Thu, 03 Dec 2015 03:25:55 GMT
    <