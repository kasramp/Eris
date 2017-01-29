# Welcome to Eris
Eris is an open source weather API for getting the current weather condition across the globe.

Quick access:

- [Details](#details)
- [Dependencies](#dependencies)
- [API docuentation](#apicall)
    - [/v1/weather/current](#current)
    - [/v1/weather/currentbyip](#currentbyip)
    - [Examples](#examples)
- [Deploy your own Eris instance](#deployment)
- [Free public Eris instance](#freeinstance)
- [Authors and Contributors](#author)
- [Support/Contact](#contact)
- [License](#license)

## <a name="details">Details</a>
Eris is a simple Spring Boot Java API that is developed as a consolidation of the following services:

- [Open Weather Map](https://openweathermap.org/)
- [Open Street Map](http://openstreetmap.org/)
- [Group Kt](http://www.groupkt.com/post/f2129b88/services.htm)
- [IP API](http://ip-api.com/)

The technology stack solely consists of [Spring Boot](http://projects.spring.io/spring-boot/) framework only.
The live version of the API service is hosted on [Red Hat OpenShift](https://www.openshift.com/).
The service can be hosted in any platform that supports Spring Boot.

Feel free to fork the project and create your own private API instance.

## <a name="dependencies">Dependencies</a>
All the project dependencies exist in pom.xml file and once your run the project, all dependencies will be downloaded.

## <a name="apicall">Call Eris APIs</a>
Eris has two APIs which both do the same thing, getting the current weather condition.

Each API designed for its own use case, though both gives the same response.

To access the APIs need to perform `get` at the following URLs:

- [/v1/weather/current](#current)
- [/v1/weather/currentbyip](#currentbyip)

### <a name="current">/v1/weather/current</a>
To use this API three parameters are required which two are compulsory and another one is optional.

The list of parameters with their description can be found below table

Parameter | Type | Compulsory
--- | --- | ---
lat | Decimal | &#10004;
lon | Decimal | &#10004;
fahrenheit | Boolean | &#10008;

### <a name="currentbyip">/v1/weather/currentbyip</a>


### <a name="examples">Examples</a>

## <a name="deployment">Deploy your own Eris instance</a>
To run and deploy the project on your local or any desired server, first clone the project and the follow the below instruction.

- Add Open Weather Map API key to `apikey.properties` that located under `resource` folder.
- Compile and run the API using Maven

        $ maven clean install
        $ cd target
        $ java -jar neat-geo-ip-1.0-SNAPSHOT.jar

## <a name="freeinstance">Free public Eris instance</a>
We have deployed a free public instance of Eris to Open Shift which is available from the following link:

[http://weather-api.madadipouya.com/](http://weather-api.madadipouya.com/)

API URLs:

- [http://weather-api.madadipouya.com/v1/weather/current](http://weather-api.madadipouya.com/v1/weather/current)
- [http://weather-api.madadipouya.com/v1/weather/currentbyip](http://weather-api.madadipouya.com/v1/weather/currentbyip)

## <a name="author">Authors and Contributors</a>
This API is developed and maintained by [Kasra Madadipouya](http://blog.madadipouya.com) ([@kasramp](https://github.com/kasramp)).
Anyone is welcome to contribute to this project.


## <a name="contact">Support/Contact</a>
Facing any problems, found bugs or having any inquiries? Just drop an email to
kasra@madadipouya.com

## <a name="license">License</a>
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
