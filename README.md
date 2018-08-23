
TODO board
=======================================


To deploy app you need <a href="http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html">Java JDK 8</a>, <a href="https://maven.apache.org/download.cgi">Maven</a>

## Running locally
```
	git clone https://github.com/SergiyDyrda/todo-application
	cd todoboard
	./mvnw spring-boot:run
	
```

By default application uses <a href="https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo">Embedded MongoDb</a>.
<p/>
In case you want to run it with own mongo database go to src/main/resources/application.properties and specify credentials 
to your database.
Then start application with 

```
   ./mvnw spring-boot:run -P mongo 
```
You app will up and running at http://localhost:8080