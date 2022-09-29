package com.ntnu.Lab6;

import javax.sound.sampled.Port;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT=1234;
    public static void main(String[] args) {
        Server s=new Server();
        s.run();
    }

    private void run() {


        try {
            ServerSocket welcomeSocket=new ServerSocket(PORT);
            System.out.println("Server Started on port: "+ PORT);

            Socket clientSocket= null;
            boolean mustrun= true;
            while (mustrun) {
                clientSocket = welcomeSocket.accept();
                InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);

                String clientInput = bufferedReader.readLine();
                System.out.println("Client Message: " + clientInput);

                String[] parts=clientInput.split(" ");


                String respose;
                if (parts.length==4) {
                    respose = parts[0]+ " "+ parts[1].toUpperCase()+" "+parts[2]+" "+parts[3];
                } else {
                    respose="Error";
                }
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println(respose);


                // close connection to particular client
                clientSocket.close();
            }
                // close the listening socket
                welcomeSocket.close();

        } catch (IOException e) {
            System.out.println( e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
