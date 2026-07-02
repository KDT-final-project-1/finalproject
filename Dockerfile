FROM eclipse-temurin:21-jdk
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jarhttps://github.com/KDT-final-project-1/finalproject/tree/main
ENV TZ=Asia/Seoul
EXPOSE 80
ENTRYPOINT java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} -Dfile.dir=/uploadtest/ app.jar
