package examples;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class ChatClient implements Runnable {
    private BufferedReader reader;

    public ChatClient(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String line = reader.readLine();
                System.out.println("M >> " + line);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 42970);

        InputStream in = socket.getInputStream();
        InputStreamReader ir = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(ir);

        OutputStream out = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(out);

        new Thread(new ChatClient(reader)).start();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(">> ");
            String message = scanner.nextLine();
            writer.println(message);
            writer.flush();
        }
    }
}
