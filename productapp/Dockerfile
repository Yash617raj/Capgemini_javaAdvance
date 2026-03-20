# step1: Use a base image, eclipse-temurin is an official OpenJDK distribution, jammy is for ubuntu
FROM eclipse-temurin:17-jdk-jammy

# step2: Set working directory inside the container, All subsequent commands will run inside /app
WORKDIR /app

# step3: Copy the JAR file from local system to container, target/* looks for any JAR inside target folder, app.jar renamed insider conatainer for simplicity
COPY target/*.jar app.jar

#step4: Define the command to run when constainer starts, this runs the Spring boot application,
ENTRYPOINT ["java","-jar","app.jar"]