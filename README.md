# url-shortener
Kotlin project for shortening a given url, stored in a local MySQL database.

#### Setting up development environment:

This application is interacting with a MySQL database so having that installed is a requirement.

Setting up the database for use in the project is not automated yet, so that needs to be done manually like so:

```mysql-sql
CREATE DATABASE url_shortener_db;
```

There has previously been issues with the MySQL-server has a different timezone set that the default timezone used by JDBC (still working on a solution for setting the timezone in the JDBI instance). So this also needs to be done manually with:

```mysql-sql
-- '+2:00' is the local timezone for this sample
SET GLOBAL time_zone = '+2:00';
``` 

Great! 🎉
Now the database should be migrated correctly, and is now ready for some local testing and development

Now just run this command to build an executable jar:
```
mvn clean install
```

#### My purpose for building this project:

While a url-shortener should not be that advanced to actually build for yourself, i decided to build this project because
i wanted to have a project to work on during my freetime.

Also, while working as a developer, all the projects i have been working on professionally has already been setup. So mainly i
built this project just to teach myself how all the technologies used in this project is setup and working.

Technologies used to help me build this project is:

* Flyway - Automated database migrations
* SLF4J - Logging
* Jackson faster-xml - serializing/deserializing json object to POJO/POKO
* JDBI - abstraction libraries for interacting with the database
* Jetty - HTTP-server
* Jersey - annotations for creating RESTful-endpoints

Testing:
* Junit5 - Running unit-tests
* Mockito - Mocking library


Packaging:
* The application has no automated way of packaging into an executable jar yet, so this can be done with:
```
mvn clean compile assembly:single
```
