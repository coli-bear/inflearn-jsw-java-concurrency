package section03.example05;

public class ThreadPriorityExample {
    public static void main(String[] args) throws InterruptedException {
        Thread maxThread = new CountingThread("우선순위가 높은 스레드", Thread.MAX_PRIORITY);
        Thread normThread = new CountingThread("보통 우선순위 스레드", Thread.NORM_PRIORITY);
        Thread minThread = new CountingThread("우선순위가 낮은 스레드", Thread.MIN_PRIORITY);

        maxThread.start();
        normThread.start();
        minThread.start();

        maxThread.join();
        normThread.join();
        minThread.join();

        System.out.println("main thread is done!");
    }

    static class CountingThread extends Thread {
        private final String threadName;
        private int count = 0;

        public CountingThread(String threadName, int priority) {
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (200_000_000_0 > count) {
                count++;
            }
            System.out.println(threadName + " : " + count);
        }
    }
}
