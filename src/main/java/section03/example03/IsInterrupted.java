package section03.example03;

public class IsInterrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
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
