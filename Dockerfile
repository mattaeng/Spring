FROM amazoncorretto:17

WORKDIR /app

ARG JAR_FILE=mattaeng-api-0.0.1-SNAPSHOT.jar

COPY ./build/libs/$JAR_FILE application.jar

CMD [ "java", "-jar", "application.jar" ]