FROM openjdk:18
WORKDIR /app
COPY ./target/upi-meta-service-0.0.1-SNAPSHOT.jar /app
EXPOSE 8081
CMD ["java", "-jar", "upi-meta-service-0.0.1-SNAPSHOT.jar"]