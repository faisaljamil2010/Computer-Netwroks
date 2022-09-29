package com.ntnu.Lab6;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client c=new Client();
        c.run();
    }

    private void run() {
        System.out.println("Client Started...");


        try {
            //Establish connection with server
            Socket socket= new Socket("127.0.0.1", 1234);
            System.out.println(" Successfully connected...");

            // send Http request to server
            String commandToSend = "Hello from the client!";
            OutputStream out=socket.getOutputStream();
            PrintWriter writer=new PrintWriter(out, true);
           writer.println(commandToSend);
           writer.println("");

            // Get  response from the server
            InputStream in = socket.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String oneResponseLine;
            do {
                oneResponseLine=reader.readLine();
                if (oneResponseLine!=null)
                {
                    System.out.println("Server Message: "+oneResponseLine);
                }
            }while (oneResponseLine!=null);

            socket.close();
        } catch (IOException e) {
            System.out.println("Socket Error:"+e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
