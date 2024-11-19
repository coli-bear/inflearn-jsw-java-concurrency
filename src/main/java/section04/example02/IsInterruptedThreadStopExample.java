package section04.example02;

public class IsInterruptedThreadStopExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            // 인터럽트가 되지 않으면 계속 실행
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is Running");
            }

            System.out.println("Thread interrupt status : " + Thread.currentThread().isInterrupted());
            System.out.println("Worker Thread is Stopped");
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            worker.interrupt(); // worker thread interrupt 발생
            System.out.println("Stopper Thread finished.");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }
}
