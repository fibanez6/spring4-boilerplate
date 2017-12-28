# SpringBoot with JPA, HIBERNATE and CUSTOM TOKEN AUTH

### REQUIREMENTS
* Java 8
* Maven
* GIT

### Installation

```
  > git pull git@github.com:fibanez6/spring4-boilerplate.git
  > cd springBoot-Jpa-CustomTokenAuth
  > mvn clean install
```

### Execution
```
  > mvn spring-boot:run
```

### REST API

* Swagger REST API Documentation
```
  > http://localhost:8080/swagger-ui.html#/
```


### Dependencies

* **spring-boot-starter-web** version 1.5.7.RELEASE
* **spring-boot-starter-data-jpa** version 1.5.7.RELEASE
* **spring-boot-starter-actuator** version 1.5.7.RELEASE
* **spring-boot-starter-security** version 1.5.7.RELEASE
* **Lombok** version 1.16.16. 

  > [Project Lombok](https://projectlombok.org/) is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again.

* **Swagger2** version 2.6.1
  > [Swagger](https://swagger.io/) is an open source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services. 
While most users identify Swagger by the Swagger UI tool, the Swagger toolset includes support for automated documentation, code generation, and test case generation.

* **h2** version 1.4.196
  > [H2](http://www.h2database.com/html/main.html) is a relational database management system written in Java. It can be embedded in Java applications or run in the client-server mode.

### Spring Boot configuration
```
server.port = 8080

###
#   Database Settings
###
spring.datasource.url=jdbc:h2:~/db/database.db;DB_CLOSE_ON_EXIT=TRUE;INIT=create schema IF NOT EXISTS test;
spring.datasource.platform=h2
spring.datasource.username =sa
spring.datasource.password =
spring.datasource.driverClassName = org.h2.Driver

###
#   H2 Settings
###
spring.h2.console.enabled=true
spring.h2.console.path=/console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=false

###
#   Hibernate Settings
###
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.properties.hibernate.ddl-auto = create-drop
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.format_sql=true
```

* **spring.datasource.***: sets up an in-memory H2 database;
* **spring.datasource.initialize**: tells spring-boot to initialize the database with scripts;
* **spring.datasource.schema**: the schema sql script to load. By default it is schema-${platform}.sql then schema.sql;
* **spring.datasource.data**: the data sql script. By default, it is data-${platform}.sql then data.sql;
* **spring.h2.console.enabled**: allow us to access the memory database from a web interface;
* **spring.jpa.hibernate.ddl-auto**: hibernates also tries to initialize the database. 
When it detects an embedded database, it sets ddl-auto to create-drop and initialize the database with entities annotated with @Table (and also looks for imports.sql). 
This may lead to creating the same table twice. I prefer to stick with Spring Boot magic, so I set this to none