package section02.example02;

public class ThreadStack {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable1(i));
            thread.start();
        }
    }
}

class MyRunnable1 implements Runnable {
    private final int threadId;

    public MyRunnable1(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println("This is an anonymous thread : " + Thread.currentThread().getName() + " " + threadId);
        this.firstMethod(this.threadId);
    }

    private void firstMethod(int threadId) {
        int localValue = threadId + 100;
        this.secondMethod(localValue);
    }

    private void secondMethod(int localValue) {
        System.out.println("=> Thread name: " + Thread.currentThread().getName());
        System.out.println("=> Thread ID  : " + this.threadId);
        System.out.println("=> localValue : " + localValue);
    }
}
