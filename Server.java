package com.chatapp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
        public static void main(String[] args)
        {
            try(ServerSocket serverSocket = new ServerSocket(5000))
            {
                while(true)
                {
                    new ClientThread(serverSocket.accept()).start();
                }
            } catch(IOException e) {
                System.out.println("Server exception " + e.getMessage());
            }
        }
}

class ClientThread extends Thread {
    private String nameOfClient;
    private Socket clientSocket;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public ClientThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run()
    {
        try
        {
            this.inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            this.nameOfClient = inputReader.readLine();
            System.out.println("--------------"+this.nameOfClient+" joined the Chat Group"+"--------------");
            while(true)
            {
                String echoString = inputReader.readLine();
                if(echoString.equals("exit"))
                {
                    System.out.println("--------------"+this.nameOfClient+" left the chat group"+"--------------");
                    break;
                }
                System.out.println(this.nameOfClient+" : "+echoString);

            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                clientSocket.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}



