package section03.example03;

public class Interrupted2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()) { // 인터럽트가 걸렸어도 상태를 변경하지 않음
                System.out.println("Thread is running : " + Thread.currentThread().isInterrupted());
            }
            System.out.println("Thread is interrupted");
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2 is running");
                if (i == 2) {
                    thread1.interrupt();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread2.start();
        thread1.start();
    }
}
