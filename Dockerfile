FROM amazoncorretto:17-alpine
RUN mkdir "/app"
COPY target/*.jar /app
CMD ["java", "-jar", "/app/walletTestApp-0.0.1-SNAPSHOT.jar"]
