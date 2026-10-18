#!/usr/bin/env bash
# Script para lanzar N clientes. Uso: ./run_clients.sh N [host] [puerto]
# Intenta abrir terminales nuevas si detecta gnome-terminal, si no ejecuta clientes en background redirigiendo a archivos.

NUM=${1:-2}
HOST=${2:-localhost}
PORT=${3:-12345}

compile() {
  mkdir -p bin
  javac -d bin src/Server.java src/Client.java
}

compile

if command -v gnome-terminal >/dev/null 2>&1; then
  for i in $(seq 1 $NUM); do
    gnome-terminal -- bash -c "java -cp bin Client $HOST $PORT; echo 'Cliente $i finalizado'; read -p 'Presiona Enter para cerrar...';"
    sleep 0.2
  done
  echo "Se abrieron $NUM terminales con clientes."
else
  echo "gnome-terminal no disponible: ejecutando clientes en background y guardando salida en client-*.log"
  for i in $(seq 1 $NUM); do
    nohup java -cp bin Client $HOST $PORT > client-$i.log 2>&1 &
    echo "Cliente $i lanzado en background (cliente-$i.log)"
  done
fi
