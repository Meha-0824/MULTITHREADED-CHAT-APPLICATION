import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to chat server!");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            // Thread for reading messages
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = input.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected from server.");
                }
            }).start();

            // Sending messages
            String message;
            while ((message = console.readLine()) != null) {
                output.println(message);
            }

        } catch (Exception e) {
            System.out.println("Unable to connect to server.");
        }
    }
}
