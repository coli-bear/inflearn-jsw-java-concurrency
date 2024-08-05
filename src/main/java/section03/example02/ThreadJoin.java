package section03.example02;

public class ThreadJoin {
    public static void main(String[] args) {
        System.out.println("=> 메인 스레드 시작");

        // 메인스레드에서 생성되는 자식 스레드
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("=> 자식 스레드 3초 대기 시작");
                Thread.sleep(3000);
                System.out.println("스레드 3초 대기 종료");
            } catch (InterruptedException e) {
                System.out.println("첫 번째 스레드 인터럽트로 인한 예외처리");
            }
        });

        thread1.start();
        try {
            System.out.println("메인 스레드에서 자식 스레드가 종료될 때까지 대기");
            thread1.join();
            System.out.println("자식 스레드 종료에 따른 메인 스레드 작업 진행");
        } catch (InterruptedException e) {
            System.out.println("메인 스레드 인터럽트로 인한 예외처리");
        }

        System.out.println("=> 메인 스레드 종료");
    }
}