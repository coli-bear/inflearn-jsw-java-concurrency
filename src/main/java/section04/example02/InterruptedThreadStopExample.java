package section04.example02;

public class InterruptedThreadStopExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            // 인터럽트가 되지 않으면 계속 실행
            while (!Thread.interrupted()) {
                System.out.println("Thread is Running");
            }

            System.out.println("Thread interrupt status 1: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt(); // 인터럽트 상태를 true로 변경
            System.out.println("Thread interrupt status 2: " + Thread.currentThread().isInterrupted());
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
