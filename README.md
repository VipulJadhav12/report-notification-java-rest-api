
# report-notification-java-rest-api

Based on a Microservice Architecture, this project has been designed and developed as a solution for "Problem Statement #3: Email Report Micro App" of "Weekly Status Reporting Project". This a simple REST API based application developed with JAVA, Spring Boot, and Spring Framework to manage Weekly-Status related Report sharing operations.


## Features

This API provides HTTP endpoints for the following operations:

- Search and find individual Project by id and its related details.
- Search and find individual Weekly-Status by id and project_id.
- Send Project wise Weekly-Status email reports.

## Requirements

- Java 1.8+
- Maven 3.0+
- Docker Engine
- Latest MySQL

## Tech Stack

- Java 1.8+
- Maven 3.0+
- Spring Boot 2.7.0+
- JUnit 5
- SpringFox Swagger2
- Docker Engine
- Latest MySQL 8.0+

## Run Locally

Clone the project

```bash
  git clone https://github.com/VipulJadhav12/report-notification-java-rest-api.git
```

Go to the project directory

```bash
  cd report-notification-java-rest-api
```

Open and edit the src/main/resources/application.properties file

```bash
  # CORS origin url from which HTTP requests will be allowed
  cors.origin.url=http://localhost:8383

  # Port no. at which this application will listen on
  server.port=9393
  server.error.include-message=always

  # Spring Datasource (database) properties
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/<DATABASE_NAME>?autoReconnect=true&useSSL=false&createDatabaseIfNotExist=true
  spring.datasource.username=<USERNAME>
  spring.datasource.password=<PASSWORD>
  spring.jpa.generate-ddl=true
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

  # Spring Boot mail server properties
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.username=<login user to smtp server>
  spring.mail.password=<login password to smtp server>
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
```

Pull the public MySQL docker image and start a docker container using it

```bash
docker pull mysql
```
```bash
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
```

Check and confirm the MySQL docker container is running

```bash
  docker ps -a
```

Access the MySQL database by using the root user with same MYSQL_ROOT_PASSWORD value

```bash
  mysql -h 127.0.0.1 -u root -pmy-secret-pw
```

Run the above source code through mvn command line

```bash
  mvn spring-boot:run
```

Compile and Package the above source code as a JAR

```bash
  mvn clean package
```
or
```bash
  mvn clean package -Dmaven.test.skip=true
```

Run the above packaged source code through java command line. For that, go to the project's target directory

```bash
  java -jar report-notification-rest-api-0.0.1-SNAPSHOT.jar
```

By default, the API will be available at

```bash
  http://localhost:<PORT_NO>/api/v1/projects
```
```bash
  http://localhost:<PORT_NO>/api/v1/weekly_statuses
```
```bash
  http://localhost:<PORT_NO>/api/v1/reports
```

## Documentation

By default, the Swagger based JSON API documentation of this project will be available at

- Swagger API Doc - [http://localhost:<PORT_NO>/swagger-ui/index.html](http://localhost:<PORT_NO>/swagger-ui/index.html)

## Running Tests

To run tests, run the following commands

Go to the project directory

```bash
  cd report-notification-java-rest-api
```

Open and edit the src/test/resources/application.properties file

```bash
  # CORS origin url from which HTTP requests will be allowed
  cors.origin.url=http://localhost:8383

  # Port no. at which this application will listen on
  server.port=9393
  server.error.include-message=always

  # Spring Datasource (database) properties
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/<DATABASE_NAME>?autoReconnect=true&useSSL=false&createDatabaseIfNotExist=true
  spring.datasource.username=<USERNAME>
  spring.datasource.password=<PASSWORD>
  spring.jpa.generate-ddl=true
  spring.jpa.show-sql=true
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

  # Spring Boot mail server properties
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.username=<login user to smtp server>
  spring.mail.password=<login password to smtp server>
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
```

Pull the public MySQL docker image and start a docker container using it

```bash
  docker pull mysql
```
```bash
  docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
```

Check and confirm the MySQL docker container is running

```bash
  docker ps -a
```

Access the MySQL database by using the root user with same MYSQL_ROOT_PASSWORD value

```bash
  mysql -h 127.0.0.1 -u root -pmy-secret-pw
```

Run the unit test-cases through mvn command line

```bash
  mvn test
```

## Authors

- [@krashnat922](https://github.com/krashnat922)
- [@sachin396](https://github.com/sachin396)
- [@VipulJadhav12](https://github.com/VipulJadhav12)

