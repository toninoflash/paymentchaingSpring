#!/bin/bash

# Lista de carpetas con proyectos Spring Boot
services=("config-server" "eureka-server" "admin" "customer" "product" "invoice" "transaction")

# Salida color
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # Sin color

echo -e "${GREEN}Iniciando la compilación de todos los servicios...${NC}"

for service in "${services[@]}"
do
  echo -e "${GREEN}Compilando $service...${NC}"
  
  if [ -d "$service" ]; then
    cd "$service"
    mvn clean package -DskipTests

    if [ $? -eq 0 ]; then
      echo -e "${GREEN}✔ $service compilado correctamente.${NC}"
    else
      echo -e "${RED}✖ Error al compilar $service.${NC}"
    fi

    cd ..
  else
    echo -e "${RED}✖ La carpeta $service no existe.${NC}"
  fi
done

echo -e "${GREEN}Proceso de compilación finalizado.${NC}"
