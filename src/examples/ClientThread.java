package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientThread implements Runnable {
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientThread(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String message = reader.readLine();
                writer.println(message);
                writer.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                break;
            }
        }
    }
}
