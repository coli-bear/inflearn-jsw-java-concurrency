package section02.example01;

public class AnonymousRunnable {
    public static void main(String[] args) {
        int i = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is an anonymous thread implemented from Runnable interface : " + Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        // to lambda
        Thread threadLambda = new Thread(() -> System.out.println("This is an anonymous thread implemented from Runnable interface : " + Thread.currentThread().getName()));

        threadLambda.start();
    }
}
