FROM openjdk:8
VOLUME webapp
ADD src/main/webapp webapp
ADD target/greatOutdoors-0.0.1-SNAPSHOT.war greatOutdoors.war
ENTRYPOINT ["java","-jar","greatOutdoors.war"]
EXPOSE 9090