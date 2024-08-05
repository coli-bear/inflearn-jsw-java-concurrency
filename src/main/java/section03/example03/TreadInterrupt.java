package section03.example03;

public class TreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("thread 1 start");
            System.out.println("thread 1 isInterrupted() = " + Thread.currentThread().isInterrupted());
        });
        Thread t2 = new Thread(() -> {
            System.out.println("thread 2 start");
            t1.interrupt();
            System.out.println("thread 1 isInterrupted() = " + t1.isInterrupted());
            System.out.println("thread 2 isInterrupted() = " + Thread.currentThread().isInterrupted());

        });

        t2.start();
        Thread.sleep(1000);
        t1.start();

        t1.join();
        t2.join();
        System.out.println("작업 완려");
    }

}
