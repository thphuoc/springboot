# springboot
example for springboot

##How to build
`mvn clean`\
`mvn install package`

##How to run
Open file: MockApiApplication.java and execute it.

##Test
```
API for register new user
http://localhost:1234/register
method: POST
body:
{
    "email": "test@email.com",
    "userName": "test",
    "password": "1234"
}
```
