package section03.example02;

public class MultiThreadJoin {
    public static void main(String[] args) {
        System.out.println("=> 메인 스레드 시작");
        Thread thread1 = new Thread(() -> {
            System.out.println("=> 첫 번째 스레드 진행");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("첫 번째 스레드 인터럽트로 인한 예외처리");
            }
            System.out.println("=> 첫 번째 스레드 완료");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("=> 두 번째 스레드 진행");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("두 번째 스레드 인터럽트로 인한 예외처리");
            }
            System.out.println("=> 두 번째 스레드 완료");
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("메인 스레드 인터럽트로 인한 예외처리");
        }

        System.out.println("=> 메인 스레드 종료");
    }
}
