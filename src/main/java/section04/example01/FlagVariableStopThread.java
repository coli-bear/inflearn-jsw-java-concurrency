package section04.example01;

public class FlagVariableStopThread {
    public static void main(String[] args) throws InterruptedException {

        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(3000);
        runnable.stop();
    }

    public static class MyRunnable implements Runnable {
        private volatile boolean running = true;

        public void stop() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                System.out.println("Thread is Running");
            }
        }
    }
}
