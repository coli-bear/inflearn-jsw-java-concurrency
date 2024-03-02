package section01.example02;

import java.util.function.Function;

public class ContextSwitching {
    public static void main(String[] args) {

        // 5개의 스레드 생성
        Thread thread1 = new Thread(new SleepFunction(1)::run);
        Thread thread2 = new Thread(new SleepFunction(2)::run);
        Thread thread3 = new Thread(new SleepFunction(3)::run);
        Thread thread4 = new Thread(new SleepFunction(4)::run);
        Thread thread5 = new Thread(new SleepFunction(5)::run);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class SleepFunction implements Runnable {
    private int threadNum;

    public SleepFunction(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread " + threadNum + " : " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}