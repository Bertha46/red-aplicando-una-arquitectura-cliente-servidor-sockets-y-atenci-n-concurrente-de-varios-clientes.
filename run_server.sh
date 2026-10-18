#!/usr/bin/env bash
# Script para compilar y ejecutar el servidor en Linux/macOS
set -e

echo "Compilando..."
mkdir -p bin
javac -d bin src/Server.java src/Client.java

PORT=${1:-12345}

echo "Iniciando servidor en el puerto $PORT (salida en server.log)..."
# Ejecutar en background y guardar PID
nohup java -cp bin Server "$PORT" > server.log 2>&1 &
PID=$!
echo $PID > server.pid

echo "Servidor lanzado (PID=$PID). Logs en server.log"
