# Usa a imagem do Maven com JDK 17 para construir o projeto
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Compila o projeto ignorando testes
RUN mvn clean package -DskipTests

# Usa a imagem do OpenJDK 17 para rodar o app
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado na etapa anterior para o container final
COPY --from=build /app/target/api-selecao-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do Spring Boot
EXPOSE 8080

# Comando para rodar o aplicativo
CMD ["java", "-jar", "app.jar"]



