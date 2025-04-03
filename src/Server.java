import java.io.*;
import java.net.*;

public class Server {
    private int PORT;

    public Server (int PORT) {
        this.PORT = PORT;
        try (ServerSocket serverSocket = new ServerSocket(this.PORT)) {
            System.out.println("Server is running... Waiting for clients.");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Input & Output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                out.println("Server received: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
