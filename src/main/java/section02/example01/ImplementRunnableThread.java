package section02.example01;

public class ImplementRunnableThread {
    public static void main(String[] args) {
        ImplementRunnable implementRunnable = new ImplementRunnable();
        Thread thread = new Thread(implementRunnable);
        thread.start();
        System.out.println("This is the main thread : " + Thread.currentThread().getName());
    }
}

class ImplementRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("This is a thread implemented from Runnable interface : " + Thread.currentThread().getName());
    }
}
