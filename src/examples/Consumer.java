package examples;

import java.util.LinkedList;
import java.util.List;

public class Consumer implements Runnable {

    private final int id;
    private final List<String> queue;

    public Consumer(int id, List<String> queue) {
        this.id = id;
        this.queue = queue;
        // this.queue = new LinkedList<String>();
    }

    @Override
    public void run() {
        String message;
        while(true) {
            synchronized(queue) {
                while(queue.size() == 0) {
                    System.out.println(id + ": Queue is empty, waiting...");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                    System.out.println(id + ":Notified!");
                }

                message = queue.remove(0);
            }

            System.out.println(id + ": " + message);
        }
    }

    public void addMessage(String message) {
        synchronized(queue) {
            queue.add(message);
            queue.notifyAll();
        }
    }

}
