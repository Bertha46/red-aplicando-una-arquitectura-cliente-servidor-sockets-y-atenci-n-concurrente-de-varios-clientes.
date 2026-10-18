import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor multi-hilo para consultas académicas.
 * - Soporta comandos: horario, aula, docente, materia, fecha, hora, ayuda, salir
 * - Soporta consultas con parámetro: por ejemplo "docente Sistemas Distribuidos"
 * - Ejecutar: java -cp bin Server [puerto]
 */
public class Server {
    private ServerSocket serverSocket;
    // Datos generales (clave -> valor)
    private final Map<String, String> datos = new HashMap<>();
    // Datos por materia (materia -> docente)
    private final Map<String, String> docentesPorMateria = new HashMap<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        initDatos();
        System.out.println("Servidor iniciado en puerto " + port);
    }

    private void initDatos() {
        datos.put("horario", "Lunes y Miércoles 08:00-10:00");
        datos.put("aula", "Aula 3B, Edificio Central");
        datos.put("docente", "Dr. Juan Pérez");
        datos.put("materia", "Sistemas Distribuidos");

        // Ejemplo de asignación por materia
        docentesPorMateria.put("sistemas distribuidos", "Dr. Juan Pérez");
        docentesPorMateria.put("arquitectura de software", "Dra. María Gómez");
        docentesPorMateria.put("programacion avanzada", "Ing. Carlos Ruiz");
    }

    public void start() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexión entrante desde " + clientSocket.getRemoteSocketAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el bucle de aceptación: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            // Ignorar
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

                out.println("Bienvenido al servidor de consultas académicas. Escribe 'ayuda' para ver comandos.");
                String line;
                while ((line = in.readLine()) != null) {
                    String trimmed = line.trim();
                    String cmd = trimmed.toLowerCase();
                    System.out.println("[" + socket.getRemoteSocketAddress() + "] " + cmd);

                    if (cmd.equals("salir")) {
                        out.println("Conexión cerrada. ¡Hasta luego!");
                        break; // cerrar solo este cliente
                    } else if (cmd.equals("ayuda")) {
                        out.println("Comandos disponibles: horario, aula, docente, materia, fecha, hora, ayuda, salir. Ejemplo: docente Sistemas Distribuidos");
                    } else if (cmd.equals("fecha")) {
                        out.println("Fecha del servidor: " + LocalDate.now().format(dateFmt));
                    } else if (cmd.equals("hora")) {
                        out.println("Hora del servidor: " + LocalTime.now().format(timeFmt));
                    } else {
                        // Soportar comandos con parámetros: "docente <materia>"
                        String[] parts = trimmed.split("\\s+", 2);
                        String key = parts[0].toLowerCase();
                        String param = parts.length > 1 ? parts[1].trim().toLowerCase() : null;

                        if (key.equals("docente") && param != null && !param.isEmpty()) {
                            String d = docentesPorMateria.get(param);
                            if (d != null) out.println("Docente de '" + parts[1].trim() + "': " + d);
                            else out.println("No se encontró información de docente para la materia '" + parts[1].trim() + "'.");
                        } else if (datos.containsKey(key)) {
                            out.println(datos.get(key));
                        } else {
                            out.println("Comando no reconocido. Escribe 'ayuda' para ver comandos.");
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error de comunicación con " + socket.getRemoteSocketAddress() + ": " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    System.out.println("Conexión cerrada con " + socket.getRemoteSocketAddress());
                } catch (IOException e) {
                    // Ignorar
                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        if (args.length >= 1) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        try {
            Server server = new Server(port);
            server.start();
        } catch (IOException e) {
            System.err.println("No se pudo iniciar el servidor: " + e.getMessage());
        }
    }
}
