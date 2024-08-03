FROM openjdk:21
VOLUME /tmp
COPY ./build/libs/poke-app.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENV TZ="Europe/Madrid"