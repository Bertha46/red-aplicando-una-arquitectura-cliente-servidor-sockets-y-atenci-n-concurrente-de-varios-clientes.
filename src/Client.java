import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente que se conecta al servidor TCP y permite múltiples consultas hasta que el
 * usuario escribe "salir".
 * Uso: java -cp bin Client [host] [puerto]
 */
public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;
        if (args.length >= 1) host = args[0];
        if (args.length >= 2) {
            try { port = Integer.parseInt(args[1]); } catch (NumberFormatException ignored) {}
        }

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
             Scanner scanner = new Scanner(System.in)) {

            // Mostrar bienvenida del servidor
            String welcome = in.readLine();
            if (welcome != null) System.out.println(welcome);

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();
                if (input == null) break;
                out.println(input); // enviar al servidor
                String response = in.readLine(); // leer respuesta
                if (response == null) {
                    System.out.println("Servidor cerró la conexión.");
                    break;
                }
                System.out.println(response);
                if (input.trim().equalsIgnoreCase("salir")) {
                    // cerramos localmente
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
