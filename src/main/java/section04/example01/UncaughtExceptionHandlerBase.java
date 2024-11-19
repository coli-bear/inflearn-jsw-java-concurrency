package section04.example01;

public class UncaughtExceptionHandlerBase {
    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler handler1 = (t, e) -> {
            System.out.println("Thread name = " + t.getName());
            System.out.println("Uncaught exception: " + e);
        };
        Thread.setDefaultUncaughtExceptionHandler(handler1);

        new Thread(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");
        }).start();

        new Thread(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");
        }).start();


        Thread thread1 = new Thread(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");
        });
        thread1.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(">> Thread 1 Uncaught exception: " + e);
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");
        });
        thread1.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(">> Thread 2 Uncaught exception: " + e);
        });

        thread1.start();
        thread2.start();
    }
}
