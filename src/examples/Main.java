package examples;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> queue = new LinkedList<>();

        Consumer consumer = new Consumer(1, queue);
        Thread consumerThread1 = new Thread(consumer);
        consumerThread1.start();

        new Thread(new Consumer(2, queue)).start();

        for(int i=0; i<5; i++) {
            Producer producer = new Producer(i, consumer);
            Thread producerThread = new Thread(producer);
            producerThread.start();
        }
    }
}
