package Code;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {
    ConcurrentLinkedQueue<String> queue;

    public Queue() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    }

    boolean checkData(String data) {
        // check data, return of het in de queue in mag
        return false;
    }

    String adjustData(String data) {
        // adjust data if checkData was false
        return "";
    }

    void addToQueue(String data) {
        if (checkData(data)) {
            queue.add(data);
        } else {
            queue.add(adjustData(data));
        }
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }

    String getData() {
        return queue.poll();

    }
}
