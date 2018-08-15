import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) {
            try (Socket clientSocket = new Socket("localhost", 5000)) {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                Scanner scanner = new Scanner(System.in);
                String message;

                System.out.println("Enter your name");
                String nameOfClient = scanner.nextLine();
                outputWriter.println(nameOfClient);

                do {
                    System.out.println("Enter your message \n Enter \"exit\" to leave the group");
                    message = scanner.nextLine();

                    outputWriter.println(message);

                } while(!message.equals("exit"));

            } catch (IOException e) {
                System.out.println("Client Error: " + e.getMessage());

            }
        }

}
