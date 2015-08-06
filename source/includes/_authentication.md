# Authentication

> Example Request

```language-curl
$ curl -X DELETE http://example.com/v1/riddles/4d7252e7-75c6-470a-b376-cf8f6b99f1f0 \
  -u user:password
```

> curl uses the -u flag to pass basic auth credentials. In the example above, we are making a delete request and pass the "user" and "password" as basic auth credentials.

Some routes require an authentication and its done via HTTP Basic Auth. See an example of such a request with curl in the right coloumn.

HTTP Basic Auth implementation is the simplest technique for enforcing access controls to web resources because it doesn't require cookies, session identifier and login pages. Rather, HTTP Basic authentication uses static, standard fields in the HTTP header which means that no handshakes have to be done in anticipation.


