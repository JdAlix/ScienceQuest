FROM openjdk:21-slim
EXPOSE 80
WORKDIR /app
COPY "SpringBootProject/target/sae-0.0.1-SNAPSHOT.jar" "/app/sae-0.0.1-SNAPSHOT.jar"
CMD ["java", "-jar", "sae-0.0.1-SNAPSHOT.jar"]