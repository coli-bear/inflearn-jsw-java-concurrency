package section04.example02;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicThreadStopExample {
    private AtomicBoolean running = new AtomicBoolean(true);
    public static void main(String[] args) {
        new AtomicThreadStopExample().start();
    }

    private void start() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("Thread 1 finished. Count = " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 finished.");
            running.set(false);
        }).start();
    }
}
