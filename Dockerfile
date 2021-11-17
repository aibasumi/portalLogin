FROM azul/zulu-openjdk:11.0.12-11.50.19
WORKDIR /opt/app
RUN mkdir logs
ARG JAR_FILE=target/authservice-0.0.1-SNAPSHOT.jar
# cp authservice-0.0.1-SNAPSHOT.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]