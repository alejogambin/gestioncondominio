# =====================================================
# DOCKERFILE PARA DESARROLLO CON SPRING BOOT + MAVEN
# Sistema de Gestión de Prácticas
# =====================================================
#
# Este Dockerfile ejecuta directamente con Maven (mvn spring-boot:run)
# IDEAL PARA DESARROLLO: Hot reload, debugging, cambios rápidos
#
# Ventajas:
# - Los cambios en código se reflejan automáticamente
# - No necesitas reconstruir la imagen constantemente
# - Puedes usar volúmenes para sincronizar src/
# - Maven descarga dependencias automáticamente
#
# Desventajas:
# - Imagen más pesada (~800MB con Maven + JDK)
# - NO recomendado para producción
#
# @author Sistema de Prácticas
# @version 1.0-dev
# =====================================================

# Usar imagen con Maven + JDK
FROM maven:3.9-eclipse-temurin-17-alpine AS build 

# Metadatos de la imagen
LABEL maintainer="proyecto gestion condominio "
LABEL description="proyecto gestion condominio - Modo Desarrollo"
LABEL version="1.0-dev"

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración primero (para aprovechar caché)
COPY pom.xml .


# Descargar dependencias (se cachea si pom.xml no cambia)
RUN mvn dependency:go-offline -B -e

# Copiar el código fuente
COPY src ./src

#----
#COMPILA Y EMPAQUETA LA APLICACION
RUN mvn clean package -DskipTsts -B -e

#verificar que el JAR se creo correctamente 
RUN ls -lh /app/target/*.jar

#etapa 2 ejecucion 
#
from eclipse-temurin:17-jre-alpine

#metadatos de la imagen final
label maintainer="gestion de condominio"
label stage ="production"

#seguridad

run addgroup -S spring && adduser -S spring -G spring

#establecer directorio de trabajo 
workdir /app 

#copiar jar desde la etapa de build
copy --from=build /app/target/*.jar app.jar 
#cambiar propietario del archivo usuario spring
run chown spring:spring app.jar

#cambiar el usuario no.root
user spring:spring


# Exponer puerto
EXPOSE 8080


env JAVA_OPTS="-Xms256m -Xmx512m"

env SPRING_PROFILES_ACTIVE=production

#HEALTHCHECK 

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    cmd wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# =====================================================
# COMANDO DE INICIO - EJECUTAR CON MAVEN
# =====================================================
# spring-boot:run ejecuta la aplicación sin crear JAR
# Permite hot reload de recursos estáticos (templates, CSS, JS)
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]

# =====================================================
# COMANDOS ÚTILES PARA ESTUDIANTES
# =====================================================
#
# 1. CONSTRUIR LA IMAGEN:
#    docker build -t springboot-dev .
#
# 2. EJECUTAR LOCALMENTE:
#    docker run -p 8080:8080 springboot-dev
#
# 3. EJECUTAR CON VOLÚMENES (HOT RELOAD COMPLETO):
#    Los cambios en src/ se reflejan automáticamente
#    
#    En Windows (PowerShell):
#    docker run -d -p 8080:8080 `
#      -v ${PWD}/src:/app/src `
#      -v ${PWD}/target:/app/target `
#      -v maven-repo:/root/.m2 `
#      --name spring-dev `
#      springboot-dev
#
#    En Linux/Mac:
#    docker run -d -p 8080:8080 \
#      -v $(pwd)/src:/app/src \
#      -v $(pwd)/target:/app/target \
#      -v maven-repo:/root/.m2 \
#      --name spring-dev \
#      springboot-dev
#
#    Nota: maven-repo es un volumen nombrado para cachear dependencias
#
# 4. EJECUTAR CON BASE DE DATOS EXTERNA:
#    docker run -d -p 8080:8080 \
#      -v ${PWD}/src:/app/src \
#      -e SPRING_DATASOURCE_URL="jdbc:mysql://host:3306/db" \
#      -e SPRING_DATASOURCE_USERNAME="user" \
#      -e SPRING_DATASOURCE_PASSWORD="pass" \
#      --name spring-dev \
#      springboot-dev
#
# 5. VER LOGS EN TIEMPO REAL:
#    docker logs -f spring-dev
#
# 6. REINICIAR DESPUÉS DE CAMBIOS EN pom.xml:
#    docker restart spring-dev
#
# 7. ENTRAR AL CONTENEDOR:
#    docker exec -it spring-dev sh
#
# 8. DETENER Y ELIMINAR:
#    docker stop spring-dev && docker rm spring-dev
#
# 9. VER CONSUMO DE RECURSOS:
#    docker stats spring-dev
#
# 10. DOCKER COMPOSE (crear docker-compose.yml):
#     version: '3.8'
#     services:
#       app:
#         build: .
#         ports:
#           - "8080:8080"
#         volumes:
#           - ./src:/app/src
#           - ./target:/app/target
#           - maven-repo:/root/.m2
#         environment:
#           - SPRING_PROFILES_ACTIVE=development
#     
#     volumes:
#       maven-repo:
#
#     Ejecutar: docker-compose up -d
#     Ver logs: docker-compose logs -f
#     Detener: docker-compose down
#
# 11. LIMPIAR CACHE DE MAVEN (si hay problemas):
#     docker volume rm maven-repo
#
# =====================================================
