FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
WORKDIR /app
USER 1000:1000
COPY target/*.jar /deployments/app.jar
ENTRYPOINT ["java","-jar","/deployments/app.jar"]