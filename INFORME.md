# Aplicación cliente-servidor con sockets TCP y atención concurrente a varios clientes en Java

## 1. Introducción
En el campo de los sistemas distribuidos, la comunicación entre procesos es esencial para permitir la interacción entre distintos programas o equipos conectados en una red. Una de las formas más comunes de lograr esta comunicación es mediante sockets, que permiten establecer una conexión entre dos entidades para intercambiar información.

En esta práctica se desarrolló una aplicación cliente-servidor en Java utilizando sockets TCP. La aplicación permite que varios clientes realicen consultas académicas al servidor y reciban las respuestas correspondientes. Cada cliente permanecerá conectado hasta que escriba el comando "salir". El servidor, por su parte, debe atender varias conexiones simultáneamente utilizando hilos de ejecución, lo que garantiza la atención concurrente de múltiples clientes.

El objetivo principal es comprender cómo funcionan la comunicación entre procesos distribuidos y la implementación de una arquitectura cliente-servidor en Java.

## 2. Objetivo general
Desarrollar una aplicación de comunicación entre procesos distribuidos mediante sockets en Java, basada en una arquitectura cliente-servidor, donde varios clientes puedan consultar información académica y el servidor responda de manera concurrente.

## 3. Objetivos específicos
- Implementar un servidor TCP en Java.
- Desarrollar un cliente que se conecte al servidor.
- Manejar consultas académicas mediante comandos como horario, aula, docente, materia, fecha, hora, ayuda y salir.
- Almacenar los datos de consulta en estructuras de datos de Java.
- Obtener la fecha y la hora del sistema de forma dinámica.
- Diseñar un servidor capaz de atender varios clientes simultáneamente mediante multihilo.
- Garantizar que cada cliente permanezca conectado hasta ingresar salir.
- Verificar el funcionamiento del sistema con varios clientes conectados al mismo tiempo.

## 4. Fundamento teórico
Los sockets son puntos finales de comunicación que permiten la transmisión de datos entre dos procesos a través de una red. En Java, se usan las clases `Socket` y `ServerSocket` para crear conexiones entre cliente y servidor. El protocolo TCP (Transmission Control Protocol) es ampliamente utilizado porque ofrece una transmisión confiable, ordenada y orientada a flujo.

En una arquitectura cliente-servidor:
- El cliente realiza las solicitudes.
- El servidor recibe, procesa y responde a la solicitud.
- El servidor puede atender a varios clientes simultáneamente mediante threads.

La programación multihilo es necesaria para atender múltiples clientes sin bloquear el sistema. En Java, esto se logra creando una clase que extiende `Thread` para cada cliente conectado.

## 5. Desarrollo de la aplicación
La práctica se desarrolló en Java con una arquitectura básica cliente-servidor:

### Servidor
- Se inicia en un puerto específico.
- Espera conexiones entrantes.
- Acepta cada cliente.
- Crea un hilo para atender cada cliente.
- Evalúa el comando que recibe.
- Devuelve la respuesta según el comando.
- Solo cierra la conexión del cliente que manda salir.

### Cliente
- Se conecta al servidor.
- Envía comandos desde la consola.
- Recibe respuestas del servidor.
- Mantiene la conexión abierta hasta que el usuario ingresa salir.

## 6. Estructura de la solución
Se implementaron dos clases principales:
- `Server.java`
- `Client.java`

El servidor usa un `HashMap` para almacenar la información académica más común, como horario, aula, docente y materia. También usa `LocalDate` y `LocalTime` para obtener la fecha y la hora actuales del sistema.

## 7. Código del servidor
El servidor se encarga de:
- abrir un `ServerSocket` en un puerto
- escuchar clientes entrantes
- aceptar cada conexión
- crear un hilo para cada cliente
- responder a consultas de tipo horario, aula, docente, materia, fecha, hora, ayuda y salir

El servidor queda activo durante toda la ejecución y solo cierra la conexión del cliente que manda salir.

## 8. Código del cliente
El cliente:
- conecta con el servidor
- lee el mensaje de bienvenida
- solicita comandos al usuario
- envía el comando al servidor
- muestra la respuesta
- continúa ejecutándose hasta escribir salir

## 9. Ejecución del programa
Para ejecutar correctamente la aplicación, se sigue este orden:
1. Iniciar el servidor.
2. Abrir uno o varios clientes.
3. Escribir comandos en cada cliente.
4. Verificar las respuestas.
5. Escribir salir para cerrar la sesión de un cliente.
6. Comprobar que el servidor sigue funcionando para los demás.

Ejemplo de ejecución:

```bash
java Server 12345
java Client localhost 12345
```

## 10. Pruebas realizadas
Se realizaron pruebas funcionales con uno, dos y tres clientes conectados al mismo tiempo.

### Prueba 1: cliente individual
Se probaron los comandos:
- ayuda
- horario
- aula
- docente
- fecha
- hora
- salir

Resultado:
- El servidor respondió correctamente.
- La conexión se cerró solo cuando el cliente escribió salir.

### Prueba 2: dos clientes simultáneos
Se abrieron dos clientes al mismo tiempo. Cada uno ejecutó consultas distintas. El servidor atendió ambas conexiones sin bloquear el servicio.

### Prueba 3: tres clientes simultáneos
Se ejecutaron tres clientes casi al mismo tiempo. El servidor respondió correctamente a todas las peticiones y se comprobó la concurrencia del sistema.

Esto demuestra que el servidor es multi-hilo y puede atender varios clientes a la vez.

## 11. Evidencias de funcionamiento
Para documentar la práctica, se recomienda incluir en el informe:
- Captura del servidor ejecutándose.
- Captura de un cliente consultando horario.
- Captura de dos clientes simultáneos.
- Captura de un cliente escribiendo salir.
- Video breve del funcionamiento del proyecto.

## 12. Conclusión
La práctica permitió comprender el funcionamiento de los sockets TCP en Java y la arquitectura cliente-servidor aplicada a sistemas distribuidos. Además, se comprobó que un servidor puede atender varios clientes concurrentemente mediante hilos, sin bloquear la atención de los demás.

El sistema desarrollado cumple con las condiciones del taller: el cliente puede enviar consultas académicas al servidor, la información se responde de forma dinámica y el servidor sigue funcionando aunque un cliente cierre su sesión con salir. Este tipo de aplicación representa un ejemplo claro de cómo se comunican procesos distribuidos en una red, una base fundamental de la computación moderna.

## 13. Bibliografía
- Tanenbaum, A. S., & Van Steen, M. (2017). Distributed Systems. Pearson.
- Oracle Java Documentation. Java Networking.
- Material de clase de Sistemas Distribuidos.

## 14. Observación final
Este trabajo fue desarrollado con el fin de aplicar los conceptos de programación distribuida, comunicación de procesos y sockets en Java, cumpliendo con los requisitos de la práctica académica.
