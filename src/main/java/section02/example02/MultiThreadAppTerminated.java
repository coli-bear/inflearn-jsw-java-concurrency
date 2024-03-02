package section02.example02;

public class MultiThreadAppTerminated {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable2(i));
            thread.start();
        }

        System.out.println("======================================");
        System.out.println("This is the end of the main thread");
        System.out.println("======================================");
    }
}

class MyRunnable2 implements Runnable {
    private final int threadId;

    public MyRunnable2(int threadId) {
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

