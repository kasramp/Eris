# Eris ![Release](https://github.com/kasramp/Eris/actions/workflows/build_and_release.yml/badge.svg) [![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=Eris&metric=alert_status)](https://sonarcloud.io/dashboard?id=Eris)

Eris is an open source weather API to get the current weather condition across the globe.

## Description

Eris is a simple Spring Boot Java API that is developed as a consolidation of the following services:
- [Open Weather Map](https://openweathermap.org/)
- [Open Street Map](http://openstreetmap.org/)
- [Group Kt](http://www.groupkt.com/post/f2129b88/services.htm)
- [IP API](http://ip-api.com/)

The technology stack consists of Spring Boot framework only. The free version of the service is also hosted on Heroku. Though the API can be hosted in any platform that supports Spring Boot. Feel free to fork it and create your private API.

## Dependencies

The project depends on JDK 17 and Maven. Make sure to have them installed. Any other project dependencies exist in pom.xml file and once you run the project, they will be downloaded.

## Development

Eris can operate in **two modes**:
  - Standalone: single node (default)
  - Cluster: multiple nodes registering with Consul

### Standalone mode

To run the project in standalone mode just run,

```bash
$ mvn spring-boot:run
```

### Cluster mode 

To test the cluster mode, you need to have the Consul agent up and running. You can use `docker-compose.yml` file included in the project,

```bash
$ docker-compose -f docker-compose.yml up
```

After that, enable the cluster mode via exporting the following environment variable,

```bash
$ export SPRING_CLOUD_CONSUL_ENABLED=true
```

Lastly, run the project,

```bash
$ mvn spring-boot:run
```

## Build JAR file

If you decide to build your own Über-JAR file to deploy either locally or on a server, after cloning the project, you have to set `openweathermap` key
and `actuator.username` and `actuator.password`. For that, modify `apikey.properties` and `application.properties` file respectively before generating
the JAR file. Alternatively, you can overwrite them those variables at runtime, see `Dockerfile` for all variable names.

To generate the Über-JAR, run:

```bash
$ maven clean install
```

To test the generated JAR file,

```bash
$ cd target
$ java -jar eris-[version]-SNAPSHOT.jar
```

## Heroku deployment

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/kasramp/Eris)

## Project & API documentation

To know more about the project structure and API documentation please refer to our Github page [documentation](https://eris.madadipouya.com/#apicall) at this link.

## Contact

* kasra@madadipouya.com

## License

<p>
<img src="https://www.gnu.org/graphics/gplv3-127x51.png" alt="License"/>
</p>
Eris Weather API is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3
as published by the Free Software Foundation.

Eris Weather API is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.  <http://www.gnu.org/licenses/>

Author(s):

© 2017-2024 Kasra Madadipouya <kasra@madadipouya.com> 

