package section03.example03;

public class InterruptedExceptions {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("스레드가 인터럽트 됨");
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }
        });

        thread.start();
        Thread.sleep(2000);

        thread.interrupt();
        thread.join();

        System.out.println("인터럽트 상태 3: " + thread.isInterrupted());

    }
}
