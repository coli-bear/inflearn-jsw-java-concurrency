package section03.example01;

public class InterruptThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("20 초 스레드 잠시 대기. 인터럽트 되지 않으면 ");
                Thread.sleep(10000);
                System.out.println("인터럽트 없이 스레드 시작");
            } catch (InterruptedException e) {
                System.out.println("스레드 인터럽트로 인한 예외처리");
            }
        });

        sleepingThread.start();

        Thread.sleep(1000);
        // main thread 에서 Thread interrupt 발생시킨다.
        sleepingThread.interrupt();
    }
}
