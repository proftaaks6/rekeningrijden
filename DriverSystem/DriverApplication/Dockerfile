FROM ubuntu as convert


RUN apt-get update && apt-get install -y dos2unix && apt-get install -y curl && curl -L -o mysql.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.34/mysql-connector-java-5.1.34.jar

COPY domain.xml domain.xml

RUN dos2unix domain.xml

FROM payara/micro as build

COPY --from=convert --chown=payara:payara mysql.jar mysql.jar
COPY --chown=payara:payara ./target/_NAME_.war deploy.war
COPY --from=convert --chown=payara:payara ./domain.xml domain.xml

#java -jar /opt/payara/payara-micro.jar  --addJars mysql.jar --domainConfig domain.xml --deploy deploy.war --outputUberJar uber.jar
RUN ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "mysql.jar", "--domainConfig", "domain.xml", "--deploy", "deploy.war", "--outputUberJar",  "uber.jar"]
CMD [""]
ENTRYPOINT [""]

FROM openjdk:8-jdk-alpine as run

COPY --from=build /opt/payara/uber.jar run.jar
CMD ["java", "-jar", "run.jar"]