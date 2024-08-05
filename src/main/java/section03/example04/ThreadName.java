package section03.example04;

public class ThreadName {
    public static void main(String[] args) {

        Thread t2 = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        t3.setName("t3");
        t3.start();

        Thread setThreadName = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        setThreadName.setName("setThreadName");
        setThreadName.start();
        Thread t = new Thread(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        });
        t.start();
    }
}
