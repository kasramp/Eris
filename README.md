# Welcome to Eris
Eris is an open source weather API for getting the current weather condition across the globe.

Quick access:

- [Details](#details)
- [Dependencies](#dependencies)
- [API documentation](#apicall)
    - [/v1/weather/current](#current)
    - [/v1/weather/currentbyip](#currentbyip)
    - [Examples](#examples)
- [Deploy your own Eris instance](#deployment)
- [Free public Eris instance](#freeinstance)
- [Authors and Contributors](#author)
- [Support/Contact](#contact)
- [License](#license)

## <a name="details">Details</a>
Eris is a simple [Spring Boot](http://projects.spring.io/spring-boot/) Java API service that is developed as a consolidation of the following services:

- [Open Weather Map](https://openweathermap.org/)
- [Open Street Map](http://openstreetmap.org/)
- [Group Kt](http://www.groupkt.com/post/f2129b88/services.htm)
- [IP API](http://ip-api.com/)

The technology stack solely consists of Spring Boot framework.
The live version of the API service is hosted on [Red Hat OpenShift](https://www.openshift.com/).
The service can be hosted in any platform that supports Spring Boot.

Feel free to fork the project and create your own private API instance.

## <a name="dependencies">Dependencies</a>
All the project dependencies exist in `pom.xml` file and once you run the project, all dependencies will be downloaded.

## <a name="apicall">API documentation</a>
Eris has two APIs which both do the same thing, getting the current weather condition.

Each API designed for its own use case, though both give the same response.

To access the APIs need to perform `get` request at the following URLs:

- [/v1/weather/current](#current)
- [/v1/weather/currentbyip](#currentbyip)

### <a name="current">/v1/weather/current</a>
This endpoint is suitable for the case that coordinates (latitude, longitude) are available.
To use this API three URL parameters are required which two are compulsory and another one is optional.

The list of parameters with their description can be found below table

Parameter | Description |Type | Compulsory
:---: | :---: | :---: | :---:
lat | Latitude | Decimal | &#10004;
lon | Longitude | Decimal | &#10004;
fahrenheit | Temperature scale, supported Fahrenheit (true) and Celsius (false, default) | Boolean | &#10008;

The JSON response of the call is something similar to below:

```
{
   "country":"Malaysia",
   "geoLocation":"Suria KLCC, KLCC-Bukit Bintang Pedestrian Walkway, Bukit Bintang, Sentul, KL, 50540, Malaysia",
   "temperature":22.54,
   "icon":"http://openweathermap.org/img/w/04n.png",
   "iconName":"04n",
   "errors":[],
   "apiVersion":"v1.0",
   "coord":{
      "lat":"3.1569485999999998",
      "lon":"101.71230299999999"
   },
   "weather":[
      {
         "id":803,
         "main":"Clouds",
         "description":"broken clouds",
         "icon":"04n"
      }
   ],
   "base":"stations",
   "main":{
      "temp":22.54,
      "pressure":997.14,
      "humidity":89,
      "temp_min":22.54,
      "temp_max":22.54,
      "sea_level":1026.06,
      "grnd_level":997.14
   },
   "visibility":null,
   "wind":{
      "speed":0.56,
      "deg":228.002
   },
   "clouds":{
      "all":68
   },
   "dt":1485705488,
   "sys":{
      "countryNameFull":"Malaysia",
      "type":0,
      "id":0,
      "message":0.0075,
      "country":"MY",
      "sunrise":1485646018,
      "sunset":1485689156
   },
   "id":1735162,
   "name":"Setapak",
   "cod":200
}
```

If none numeric `lat` and/or `lon` given to the endpoint, instead of throwing `400 Bad Request`,
the API returns the following response,

```
{
   "country":null,
   "geoLocation":null,
   "temperature":null,
   "icon":null,
   "iconName":null,
   "errors":["Invalid latitude and/or longitude provided!"],
   "apiVersion":"v1.0",
   "coord":null,
   "weather":null,
   "base":null,
   "main":null,
   "visibility":null,
   "wind":null,
   "clouds":null,
   "dt":null,
   "sys":null,
   "id":null,
   "name":null,
   "cod":null
}
```

### <a name="currentbyip">/v1/weather/currentbyip</a>
This point suits when no coordinates are available. As a result, the weather condition retrieved via the caller IP address location.
Obviously, compare with the `/current`, this endpoint has lower accuracy.
The reason for this is because coordinates acquired based on IP address which is usually not the same with the user's location.
In most cases, the location of the IP refers to the nearest ISP center that the user is connected to.
The API parameters are as follows:

Parameter | Description |Type | Compulsory
:---: | :---: | :---: | :---:
fahrenheit | Temperature scale, supported Fahrenheit (true) and Celsius (false, default) | Boolean | &#10008;

The IP address retrieved automatically from request header and as it can be seen, no parameter is defined to manually determine it.
The response of this endpoint is identical with the `/current` one.

### <a name="examples">Examples</a>
To call the endpoints, you need to send `get` request with appropriate URL parameters.

The request URL for `/current` should be something similar to this.

[http://weather-api.madadipouya.com/v1/weather/current?lat=3.15694859&lon=101.7123029&fahrenheit=false](http://weather-api.madadipouya.com/v1/weather/current?lat=3.15694859&lon=101.7123029&fahrenheit=false)

Consequently, the request URL for `/currentbyip` should be similar to below:

[http://weather-api.madadipouya.com/v1/weather/currentbyip?fahrenheit=false](http://weather-api.madadipouya.com/v1/weather/currentbyip?fahrenheit=false)

In Unix/Linux, you can use `curl` command to call the service.

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://weather-api.madadipouya.com/v1/weather/current?lat=3.15694859&lon=101.7123029&fahrenheit=false'`

Or

`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://weather-api.madadipouya.com/v1/weather/currentbyip?fahrenheit=false'`

## <a name="deployment">Deploy your own Eris instance</a>
To run and deploy the project on your local or any desired server, first clone the project and then follow the below instructions.

- Add Open Weather Map API key to `apikey.properties` that located under `resource` folder.
- Compile and run the API using Maven

        $ maven clean install
        $ cd target
        $ java -jar eris-0.1.0-SNAPSHOT.jar

## <a name="freeinstance">Free public Eris instance</a>
We have a free running public instance of Eris hosted on Open Shift. The instance is available from the following link:

[http://weather-api.madadipouya.com/](http://weather-api.madadipouya.com/)

API URLs:

- [http://weather-api.madadipouya.com/v1/weather/current](http://weather-api.madadipouya.com/v1/weather/current)
- [http://weather-api.madadipouya.com/v1/weather/currentbyip](http://weather-api.madadipouya.com/v1/weather/currentbyip)

## <a name="author">Authors and Contributors</a>
This API is developed and maintained by [Kasra Madadipouya](http://blog.madadipouya.com) ([@kasramp](https://github.com/kasramp)).
Anyone is welcome to contribute to this project.


## <a name="contact">Support/Contact</a>
Facing any problems, found bugs or having any inquiries? Just drop an email to
<kasra@madadipouya.com>

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
