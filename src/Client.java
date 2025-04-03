import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String SERVER_ADDRESS; 
    private int PORT = 12345;

    public Client(String ADRESS, int PORT) {
        this.PORT = PORT;
        this.SERVER_ADDRESS = ADRESS;
        try (Socket socket = new Socket(this.SERVER_ADDRESS, this.PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server!");
            
            String userMessage;
            while (true) {
                System.out.print("Enter message: ");
                userMessage = userInput.readLine(); 

                if (userMessage.equalsIgnoreCase("exit")) {
                    break; 
                }

                out.println(userMessage);
                System.out.println("Server: " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
