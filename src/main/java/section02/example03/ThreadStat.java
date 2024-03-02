package section02.example03;

public class ThreadStat {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            int j = 1;
            while (j != 0) {
                for (int i = 0; i < 100000000; i++) {
                    if (i % 100000000 == 0) {
                        // 여기는 스레드가 실행중
                        System.out.println("Thread state 3: " + Thread.currentThread().getState());
                        j--;
                    }
                }
            }
        });

        // 스레드 생성
        System.out.println("Thread state 1: " + thread.getState());

        thread.start();
        System.out.println("Thread state 2: " + thread.getState());

        Object lock = new Object();
        Thread lockThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        lockThread.start();
        Thread.sleep(100);
        // wait 로 대기중
        System.out.println("Thread state 4: " + lockThread.getState());

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
        Thread.sleep(100);
        // Time Waiting
        System.out.println("Thread state 5: " + thread3.getState());

        Object lock2 = new Object();
        Thread thread44 = new Thread(() -> {
            synchronized (lock2) {
                while (true) {
                    // 무한 루프
                }
            }
        });

        thread44.start();
        Thread thread4 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("락 획득??");
            }
        });

        thread4.start();
        Thread.sleep(100);
        // Blocked
        System.out.println("Thread state 6: " + thread4.getState());

        thread.join();

        // 종료
        System.out.println("Thread state 7: " + thread.getState());
    }
}
