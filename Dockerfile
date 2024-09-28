FROM bellsoft/liberica-openjdk-alpine:17 as builder

# JAR/WAR 파일의 위치에 따른 변수 설정
ARG WAR_FILE=./build/libs/*.war

ARG PROFILES
ARG ENV
COPY ${WAR_FILE} app.war

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dserver.env=${ENV}", "-jar", "app.war"]