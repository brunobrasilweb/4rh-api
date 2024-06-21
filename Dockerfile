# Use a base image with Java 11
FROM adoptopenjdk:11-jre-hotspot

# Copy the JAR file to the container
COPY --from=build /target/*.jar app.jar
# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE 8080
# Define the command to run your application
CMD ["java", "-jar", "app.jar"]