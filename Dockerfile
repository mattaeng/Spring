FROM amazoncorretto:17

ARG APP_DIR=/opt/app/api/classes/

WORKDIR ${APP_DIR}

CMD [ "java", "-Dspring.profiles.active=dev", "-cp", ".:./*", "com.mattaeng.mattaengapi.MattaengApiApplication" ]