FROM amazoncorretto:17 as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:17
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENV OPENWEATHERMAP_API_KEY=openweatherapikey
ENV SEGMENTIO_WRITE_API_KEY=segmentioapikey
ENV ACTUATOR_USERNAME=username
ENV ACTUATOR_PASSWORD=password
ENV SPRING_CLOUD_CONSUL_ENABLED=false
ENV SPRING_CLOUD_CONSUL_HOST=localhost
ENV SPRING_CLOUD_CONSUL_PORT=8500

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]