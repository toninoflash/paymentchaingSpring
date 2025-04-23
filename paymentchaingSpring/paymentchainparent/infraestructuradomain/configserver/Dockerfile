# Imagen base con JRE 21 (distroless o slim para menor peso)
FROM eclipse-temurin:21-jre as base

# Variables de entorno para el usuario y grupo
ENV USERNAME=admin \
    GROUPNAME=sevpsc \
    APP_HOME=/app
# Crear grupo y usuario sin privilegios (rootless)
RUN groupadd --system $GROUPNAME && \
    useradd --system --gid $GROUPNAME --create-home --home-dir $APP_HOME $USERNAME

# Crear el directorio de la app y dar permisos
RUN mkdir -p $APP_HOME && \
    chown -R $USERNAME:$GROUPNAME $APP_HOME

# Directorio de trabajo
WORKDIR $APP_HOME

# Copiar el JAR a la imagen (se inyectará en tiempo de build)
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

# Cambiar a usuario sin privilegios
USER $USERNAME

# Comando por defecto
ENTRYPOINT ["java", "-jar", "app.jar"]

