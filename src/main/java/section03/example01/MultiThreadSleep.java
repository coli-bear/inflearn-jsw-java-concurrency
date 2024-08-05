package section03.example01;

public class MultiThreadSleep {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("thread1 1초 후 메시지 출력");
                Thread.sleep(1000);
                System.out.println("thread1 꺠어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("thread2 2초 후 메시지 출력");
                Thread.sleep(2000);
                System.out.println("thread2 꺠어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("메인 스레드");
    }

}
