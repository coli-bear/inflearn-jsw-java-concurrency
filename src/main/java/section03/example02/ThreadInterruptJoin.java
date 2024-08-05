package section03.example02;

public class ThreadInterruptJoin {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread longRunningThread = new Thread(() -> {
            try {
                System.out.println("=> 자식 스레드 20초 대기 시작");
                for (int i = 0; i < 20; i++) {
                    System.out.println("=> 작업 진행 : " + i);
                    Thread.sleep(1000);
                }
                System.out.println("=> 자식 스레드 20초 대기 종료");
            } catch (InterruptedException e) {
                // 이 부분은 interruptThread 에서 interrupt() 호출시에만 실행된다.
                mainThread.interrupt();
                System.out.println("자식 스레드 인터럽트로 인한 예외처리");
            }
        });

        longRunningThread.start();

        Thread interruptingThread = new Thread(() -> {
            try {
                System.out.println("인터럽트 스레드가 2초 후 긴 작업 스레드 인터럽트");
                Thread.sleep(2000);
                longRunningThread.interrupt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        interruptingThread.start();

        try {
            System.out.println("메인 스레드가 긴 작업 스레드 완료 대기");
            longRunningThread.join();
            System.out.println("긴작업 완료 ");
        } catch (InterruptedException e) {
            System.out.println("메인 스레드 인터럽트로 인한 예외처리");
        }
    }
}

