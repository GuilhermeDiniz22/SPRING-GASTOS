# Usa a imagem do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven/Gradle para o container
COPY target/api-selecao-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do Spring Boot (Render já configura isso automaticamente)
EXPOSE 8080

# Comando para rodar o aplicativo
CMD ["java", "-jar", "app.jar"]


