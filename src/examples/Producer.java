package examples;

import java.util.Random;

public class Producer implements Runnable {
    private static final Random RNG = new Random(1);

    private final int id;
    private final Consumer consumer;

    public Producer(int id, Consumer consumer) {
        this.id = id;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        int number = 0;
        while(true) {
            try {
                Thread.sleep(RNG.nextInt(4000) + 1000);
            } catch (InterruptedException e) {}

            consumer.addMessage("Producer " + id + ": message #" + number++);
        }
    }
}
