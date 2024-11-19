package section04.example02;

public class FlagThreadStopExample {
    private static volatile boolean running = true;
//    private static boolean running = true;

    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("Thread 1 finished. Count = " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread 2 finished.");
            running = false; // 먼저 캐시에 저장되고 나중에 메인 메모리에 저장(OS가 언제 쓸지 모름)
        }).start();
    }
}
