####
# This Dockerfile uses a multi-stage build to minimize the final image size.
# Stage 1: Build the application using Maven
# Stage 2: Create a minimal runtime image with just the application
####

## Stage 1: Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests -B

## Stage 2: Runtime stage
FROM registry.access.redhat.com/ubi8/openjdk-17:1.18

ENV LANGUAGE='en_US:en'

# Copy the built JAR from the build stage
COPY --from=build --chown=185 /app/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=185 /app/target/quarkus-app/*.jar /deployments/
COPY --from=build --chown=185 /app/target/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=185 /app/target/quarkus-app/quarkus/ /deployments/quarkus/

# Expose the application port
EXPOSE 8080

# Set the user to non-root
USER 185

# Set environment variables for production
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

# Run the application
ENTRYPOINT [ "java", "-jar", "/deployments/quarkus-run.jar" ]
