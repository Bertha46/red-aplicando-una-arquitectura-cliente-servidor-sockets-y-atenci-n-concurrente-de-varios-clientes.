@echo off
REM Script para Windows: abre N clientes en nuevas ventanas
REM Uso: run_clients.bat N [host] [puerto]
set NUM=%1
if "%NUM%"=="" set NUM=2
set HOST=%2
if "%HOST%"=="" set HOST=localhost
set PORT=%3
if "%PORT%"=="" set PORT=12345

REM Compilar (asume javac en PATH)
if not exist bin mkdir bin
javac -d bin src\Server.java src\Client.java

for /L %%i in (1,1,%NUM%) do (
  start cmd /k java -cp bin Client %HOST% %PORT%
)
