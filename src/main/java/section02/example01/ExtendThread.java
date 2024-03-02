package section02.example01;

public class ExtendThread extends Thread {
    public static void main(String[] args) {
        ExtendThread thread = new ExtendThread();
        thread.start();
        System.out.println("This is the main thread : " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("This is a thread extended from Thread class : " + Thread.currentThread().getName());
    }
}
