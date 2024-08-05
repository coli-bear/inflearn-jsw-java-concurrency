package section03.example03;

public class Interrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(!Thread.interrupted()) { // 인터럽트가 걸렸어도 상태를 변경하지 않음
                System.out.println("Thread is running : " + Thread.currentThread().isInterrupted());
            }
            System.out.println("Thread is interrupted");
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
        });

        thread.start();
        try {
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
