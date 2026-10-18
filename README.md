# Cliente-Servidor de consultas académicas (Java TCP)

Proyecto para la práctica de Sistemas Distribuidos: aplicación cliente-servidor mediante sockets TCP, con servidor multi-hilo y clientes que permanecen conectados hasta que el usuario escriba "salir".

Contenido del repositorio
- src/Server.java        -> Código del servidor
- src/Client.java        -> Código del cliente
- run_server.sh          -> Script para compilar y ejecutar el servidor (Linux/macOS)
- run_clients.sh         -> Script para ejecutar varios clientes (Linux/macOS)
- run_clients.bat        -> Script para Windows (abre varias ventanas)
- evidencia-checklist.md -> Lista de evidencias/capturas/video
- .gitignore             -> Ignorar bin/, *.class, etc.

Requisitos
- Java JDK 8 o superior
- Terminal (Linux/macOS) o CMD/PowerShell en Windows

Compilar manualmente
1. Crear carpeta bin:
   mkdir -p bin
2. Compilar las clases:
   javac -d bin src/Server.java src/Client.java

Ejecutar
1. Ejecutar el servidor (en una terminal):
   java -cp bin Server 12345
   (si no pasas puerto se usa 12345 por defecto)

2. Ejecutar uno o varios clientes (en otras terminales):
   java -cp bin Client localhost 12345

Comandos soportados desde el cliente
- horario
- aula
- docente
- materia
- fecha
- hora
- ayuda
- salir

Ejemplos avanzados
- Puedes pedir un docente por materia (parámetro):
  docente Sistemas Distribuidos
  docente "Programación Avanzada"

Scripts de ayuda
- run_server.sh    compila y lanza el servidor en segundo plano (Linux/macOS)
- run_clients.sh   intenta abrir varios clientes (si tiene gnome-terminal) o ejecuta clientes en background
- run_clients.bat  abre varias ventanas en Windows usando "start"

Evidencias
- Ver `evidencia-checklist.md` para las capturas y el video que debes incluir en tu informe.

Siguientes pasos (opcional)
- Puedo crear una rama feature/cliente-servidor si quieres separar estos archivos del main.
- Puedo añadir pruebas unitarias, logging a archivo, o usar ExecutorService para manejar hilos.
