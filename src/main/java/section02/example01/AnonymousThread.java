package section02.example01;

public class AnonymousThread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("This is an anonymous thread : " + Thread.currentThread().getName());
            }
        };

        thread.start();
    }
}
