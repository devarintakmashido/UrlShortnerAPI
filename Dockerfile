# 1. Use the "jammy" Ubuntu-based image which supports Mac Apple Silicon chips
FROM eclipse-temurin:21-jdk-jammy

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy our compiled JAR file from our computer into the container
COPY target/*.jar app.jar

# 4. Expose port 8080 so the outside world can talk to our app
EXPOSE 8080

# 5. The command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]