package Code;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {
    ConcurrentLinkedQueue<String> queue;

    public Queue() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();
    }

    private boolean checkData(String data) {
        // check data, return of het in de queue in mag
        return false;
    }

    private String adjustData(String data) {
        // adjust data if checkData was false
        return "hoi";
    }

    private void addToQueue(String data) {
        if (checkData(data)) {
            queue.add(data);
        } else {
            queue.add(adjustData(data));
        }
    }

    private int queueLength() {
        return queue.size();
    }
}
