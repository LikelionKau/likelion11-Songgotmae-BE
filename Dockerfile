FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
#WORKDIR = /usr/src/app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=stage","app.jar"]


