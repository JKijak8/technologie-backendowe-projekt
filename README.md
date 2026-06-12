# technologie-backendowe-projekt

---

## What is this repository?

This repository is a **backend** for a mock **transport company management system** made as a university project by [JKijak8](https://github.com/JKijak8), [orthdm](https://github.com/orthdm), and [panmichau](https://github.com/panmichau).

---

## How to run the program

First of all, note that this repository contains **backend only**. To actually use it, you will either need tools, such as [Postman](https://www.postman.com/), or a frontend app, you can use the one found [here](https://github.com/panmichau/projekt-frontend). The instructions on running the frontend can be found on the repository page.

Regardless of the run method you choose, you always should replace the SSL key included with this project with your own, as the key included in this project is **development only**, and it won't work on your machine.

### Docker

The easiest way to run the app, and the one recommended by us is by using [Docker](https://www.docker.com/). To run it you'll need to create three files - **docker.env**, **database.env**, and **flyway.env** in the main project directory. You can find templates for those files in the project itself, specifically [docker.env.example](https://github.com/JKijak8/technologie-backendowe-projekt/blob/main/docker.env.example), [database.env.example](https://github.com/JKijak8/technologie-backendowe-projekt/blob/main/database.env.example) and [flyway.env.example](https://github.com/JKijak8/technologie-backendowe-projekt/blob/main/flyway.env.example). Let's go through them one by one:

#### docker.env

```
DB_ADDRESS=db:5432/backend_projekt  - this line should always stay the same as it is configured this way in the dockerfiles
DB_USERNAME=postgres - this line should always stay the same as it is configured this way in the dockerfiles
DB_PASSWORD={Desired database password}
SSL_PASSWORD={SSL key password}
KEY_ALIAS={SSL key alias}
JWT_SECRET={JWT Secret}
EXTERNAL_API_URL=https://api.openweathermap.org/data/3.0/onecall - this line should always stay the same, as the API uses this external API.
EXTERNAL_API_KEY={Open Weather API key}
```

#### database.env

```
POSTGRES_PASSWORD={Desired database password}
POSTGRES_DB=backend_projekt - this line should always stay the same as it is configured this way in the dockerfiles
```

#### flyway.env

```
FLYWAY_URL=jdbc:postgresql://db:5432/backend_projekt - this line should always stay the same as it is configured this way in the dockerfiles
FLYWAY_USER=postgres - this line should always stay the same as it is configured this way in the dockerfiles
FLYWAY_PASSWORD={Desired database password}
```

Note that if a `{property}` name is repeated more than once, it **should remain the same across all of the files**. You can also change any of the properties, as long as you edit the Dockerfiles accordingly, except the `EXTERNAL_API_URL`, which must remain the same, unless you edit the code itself.

#### Running the app

The app then can be ran using `docker-compose up` in the project directory.

### Running with Java

To run the project using Java, you'll first need to *compile it*. To do this, you'll need to install [Maven](https://maven.apache.org/). You'll also need to set up a [PostgreSQL](https://www.postgresql.org.pl/) server, and run all the migrations in [technologie-backendowe-projekt/src/main/resources/db/migration](https://github.com/JKijak8/technologie-backendowe-projekt), to do that you can use a migration tool like [Flyway](https://www.red-gate.com/products/flyway/community/), or run the migrations by hand using Postgres CLI.

#### Compilation

To compile the project you need to run the following command in the project directory:

```bash
mvn clean package
```

#### Environment variables

Before running the app you'll need to add environment variables to the environment you'll be running the app in. The template for this can be found in the project files in [.env.example](https://github.com/JKijak8/technologie-backendowe-projekt/blob/main/.env.example) file.

```
DB_ADDRESS={Database address}
DB_USERNAME={Database username}
DB_PASSWORD={Database password}
SSL_PASSWORD={SSL certificate password}
KEY_ALIAS={SSL key alias}
JWT_SECRET={JWT Secret}
EXTERNAL_API_URL=https://api.openweathermap.org/data/3.0/onecall - this line should always stay the same as the app uses open weather API
EXTERNAL_API_KEY={Open weather API key}
```

#### Running the app

```bash
java -jar target/{application name}.jar
```

---

## Technologies used

This app was primarily made using [Java](https://www.java.com/pl/) and [Spring Boot](https://spring.io/projects/spring-boot), with [Maven](https://maven.apache.org/) as the package manager. The rest of the dependencies can be found in the project files in [pom.xml](https://github.com/JKijak8/technologie-backendowe-projekt/blob/main/pom.xml).