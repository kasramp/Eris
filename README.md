DDD
# Eris [![Build Status](https://travis-ci.org/kasramp/Eris.svg?branch=develop)](https://travis-ci.org/kasramp/Eris)

Eris is an open source weather API to get the current weather condition across the globe.

## Description
Eris is a simple Spring Boot Java API that is developed as a consolidation of the following services:
- [Open Weather Map](https://openweathermap.org/)
- [Open Street Map](http://openstreetmap.org/)
- [Group Kt](http://www.groupkt.com/post/f2129b88/services.htm)
- [IP API](http://ip-api.com/)

The technology stack consists of Spring Boot framework only. The free version of the service is also hosted on Red Hat OpenShift. Though the API can be hosted in any platform that supports Spring Boot. Feel free to fork it and create your private API.

## Dependencies
All the project dependencies exist in pom.xml file and once your run the project, all dependencies will be downloaded.

## How to use
To run and deploy the project on your local or any desired server, first clone the project and the follow the below instruction.
- Add Open Weather Map API key to `apikey.properties` that located under `resource` folder.
- Compile and run the API using Maven

        $ maven clean install
        $ cd target
        $ java -jar eris-0.1.0-SNAPSHOT.jar

## Project & API documentation
To know more about the project structure and API documentation please refer to our Github page [documentation](http://eris.madadipouya.com/#apicall) at this link.

## Contact
* kasra@madadipouya.com
* kasra_mp@live.com

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

© 2017 Kasra Madadipouya <kasra@madadipouya.com>

