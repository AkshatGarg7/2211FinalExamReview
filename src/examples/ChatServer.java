package examples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {


    public static void main(String[] args) throws IOException  {
        ServerSocket server = new ServerSocket(42970);

        System.out.println("Waiting for first client...");
        Socket client1 = server.accept();
        System.out.println("First client connected!");
        InputStream in1 = client1.getInputStream();
        InputStreamReader ir1 = new InputStreamReader(in1);
        BufferedReader reader1 = new BufferedReader(ir1);

        OutputStream out1 = client1.getOutputStream();
        PrintWriter writer1 = new PrintWriter(out1);

        System.out.println("Waiting for second client...");
        Socket client2 = server.accept();
        System.out.println("Second client connected!");
        InputStream in2 = client2.getInputStream();
        InputStreamReader ir2 = new InputStreamReader(in2);
        BufferedReader reader2 = new BufferedReader(ir2);

        OutputStream out2 = client2.getOutputStream();
        PrintWriter writer2 = new PrintWriter(out2);

        new Thread(new ClientThread(reader1, writer2)).start();
        new Thread(new ClientThread(reader2, writer1)).start();
        System.out.println("Client threads started!");
    }
}
