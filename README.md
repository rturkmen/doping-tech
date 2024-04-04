How can you run the project ?

Firstly, You need to install Docker on your computer.
After that you have set JAVA_HOME environment variables as java 21 .Then you can run following commands below in project root directory

-> mvn clean install
-> docker-compose build
-> docker-compose up
H2 database
path: http://localhost:8080/h2
url: jdbc:h2:mem:test:db
username: sa
there is no password on h2 database .You can enter db without password
How can you use this api ?
You can access the swagger from this link -> http://localhost:8080/swagger-ui.html
