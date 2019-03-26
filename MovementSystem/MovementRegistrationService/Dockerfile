FROM payara/micro as build

COPY --chown=payara:payara ./target/Rekeningrijden.war deploy.war
COPY --chown=payara:payara ./domain.xml domain.xml
COPY --chown=payara:payara ./mysql-connector-java-8.0.15.jar mysql.jar

#java -jar /opt/payara/payara-micro.jar  --addJars mysql.jar --domainConfig domain.xml --deploy deploy.war --outputUberJar uber.jar
RUN ["java", "-jar", "/opt/payara/payara-micro.jar", "--addJars", "mysql.jar", "--domainConfig", "domain.xml", "--deploy", "deploy.war", "--outputUberJar",  "uber.jar"]
CMD [""]
ENTRYPOINT [""]

FROM openjdk:8-jdk-alpine as run

COPY --from=build /opt/payara/uber.jar run.jar
CMD ["java", "-jar", "run.jar"]