# FROM eclipse-temurin:21-jdk AS build
# WORKDIR /app
# COPY pom.xml .
# COPY .mvn .mvn
# COPY mvnw .
# COPY src src
# RUN ./mvnw clean package -DskipTests

# FROM eclipse-temurin:21-jre
# COPY --from=build /app/target/TitoSampleAPI-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8081
# ENTRYPOINT ["java", "-jar", "/app.jar"]

# FROM eclipse-temurin:21-jdk AS build
# WORKDIR /app
# COPY pom.xml .
# COPY .mvn .mvn
# COPY mvnw .
# RUN chmod +x ./mvnw
# COPY src src
# RUN ./mvnw clean package -DskipTests

# FROM eclipse-temurin:21-jre
# COPY --from=build /app/target/TitoSampleAPI-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8085
# ENTRYPOINT ["java", "-jar", "/app.jar"]

# -------- BUILD STAGE --------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 1. Copy only Maven config first
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw

# 2. Download dependencies (cached)
RUN ./mvnw dependency:go-offline

# 3. Copy source code
COPY src src

# 4. Build
RUN ./mvnw package -DskipTests

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/TitoSampleAPI-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

