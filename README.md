# Aplicación cliente-servidor con sockets TCP (Práctica de Sistemas Distribuidos)

Este repositorio contiene la práctica desarrollada para la materia de Sistemas Distribuidos: una aplicación cliente-servidor en Java que utiliza sockets TCP y atención concurrente a varios clientes mediante hilos.

---

## Contenido del repositorio
- `src/Server.java`  → Código del servidor (multi-hilo)
- `src/Client.java`  → Código del cliente (conexión persistente hasta `salir`)
- `run_server.sh`    → Script para compilar y ejecutar el servidor (Linux/macOS)
- `run_clients.sh`   → Script para lanzar varios clientes (Linux/macOS)
- `run_clients.bat`  → Script para Windows (abre ventanas con clientes)
- `evidencia-checklist.md` → Lista de capturas y video sugeridos
- `.gitignore`       → Ignora `bin/`, clases compiladas y logs

---

## Resumen
La aplicación permite que varios clientes se conecten a un servidor TCP y realicen consultas académicas usando palabras clave (sin distinción entre mayúsculas/minúsculas). El servidor responde con información almacenada en memoria (horario, aula, docente, materia) o con datos dinámicos (fecha y hora). Cada cliente mantiene la conexión hasta que envía el comando `salir`, momento en el cual solo se cierra su conexión sin afectar al servidor ni a otros clientes.

---

## Comandos soportados
- `horario`
- `aula`
- `docente`
- `materia`
- `fecha`
- `hora`
- `ayuda`
- `salir`

También se soporta una consulta con parámetro para buscar docente por materia, por ejemplo:
- `docente Sistemas Distribuidos`

---

## Requisitos
- Java JDK 8 o superior
- Terminal (Linux/macOS) o CMD/PowerShell (Windows)

---

## Compilación y ejecución (línea de comandos)
1. Compilar las clases:
   ```bash
   mkdir -p bin
   javac -d bin src/Server.java src/Client.java
   ```

2. Iniciar el servidor (en una terminal):
   ```bash
   java -cp bin Server 12345
   ```
   Si no se especifica puerto, el servidor usa `12345` por defecto.

3. Abrir una o varias terminales adicionales y ejecutar los clientes:
   ```bash
   java -cp bin Client localhost 12345
   ```

4. Desde cada cliente, enviar comandos y leer las respuestas. Para cerrar una sesión:
   - Escribir `salir` en el cliente; solo esa conexión se cerrará.

---

## Scripts incluidos
- `run_server.sh`:
  - compila y lanza el servidor en background (Linux/macOS). Genera `server.log` y `server.pid`.
- `run_clients.sh`:
  - intenta abrir N clientes en terminales nuevas (usa `gnome-terminal` si está disponible) o lanza clientes en background guardando logs `client-*.log`.
- `run_clients.bat`:
  - para Windows; compila y abre varias ventanas con clientes usando `start`.

---

## Estructura y puntos clave del código
- `Server.java`:
  - Crea un `ServerSocket` y espera conexiones.
  - Por cada conexión acepta, instancia y lanza un `ClientHandler` (clase interna que extiende `Thread`).
  - Responde a comandos básicos y a consultas con parámetros (`docente <materia>`).
  - Obtiene fecha y hora del sistema con `LocalDate` y `LocalTime`.

- `Client.java`:
  - Se conecta al servidor mediante `Socket`.
  - Envía comandos desde la entrada estándar y muestra la respuesta.
  - Mantiene la conexión hasta que el usuario escribe `salir`.

---

## Pruebas realizadas
1. Prueba con 1 cliente: se verificó el correcto envío/recepción de comandos y el cierre con `salir`.
2. Prueba con 2 clientes simultáneos: ambos clientes realizaron consultas en paralelo y recibieron respuestas sin bloqueo.
3. Prueba con 3 clientes casi simultáneos: se comprobó la concurrencia y que el servidor siguió aceptando nuevas conexiones.

---

## Evidencias (sugeridas)
Incluye estas capturas y un video corto (30–60s) en el informe:
- `server-01.png`: servidor iniciado y a la espera.
- `client1-01.png`: cliente 1 consultando `ayuda`, `horario`, `materia`.
- `client2-01.png`: cliente 2 consultando `fecha`, `hora`.
- `client3-01.png` (opcional): tercer cliente concurrente.
- `salida-salir.png`: cliente enviando `salir` y cerrando solo su conexión.
- `video-demo.mp4`: video demostrativo mostrando inicio del servidor, lanzamiento de varios clientes, consultas y cierre de uno de los clientes.

Recomendación: utiliza nombres de archivo descriptivos y añade subtítulos o notas en el video indicando los pasos.

---

## Posibles mejoras
- Sustituir creación directa de hilos por un `ExecutorService` con pool de hilos.
- Almacenar los datos académicos en un archivo (JSON/CSV) o en una base de datos ligera (SQLite) para facilitar mantenimiento.
- Añadir autenticación simple por usuario y logging a archivo por cliente.
- Añadir manejo de excepciones más robusto y pruebas automatizadas.

---

## Autoría
- Proyecto subido por: `Bertha46`
- Integrantes recomendados: [Nombre 1], [Nombre 2], [Nombre 3]

---

Si deseas que deje el informe académico completo (más extenso) en un archivo `INFORME.md` o en formato Word (`INFORME.docx`) dentro del repositorio, lo creo y subo ahora mismo. ¿Deseas que lo añada?