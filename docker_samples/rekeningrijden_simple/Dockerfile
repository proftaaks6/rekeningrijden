FROM payara/micro

#TODO!!!! change target/deploy.jar to the expected name
COPY --chown=payara:payara target/deploy.jar Deploy.jar


COPY --chown=payara:payara ./docker_rootdir /opt/payara/rootdir

RUN ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "Deploy.jar", "--rootdir", "/opt/payara/rootdir", "--outputUberJar",  "SecureApp.jar"]
CMD [""]
ENTRYPOINT ["java", "-jar", "SecureApp.jar"]
