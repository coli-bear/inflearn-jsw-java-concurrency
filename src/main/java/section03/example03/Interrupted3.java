package section03.example03;

public class Interrupted3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (true) { // 인터럽트가 걸렸어도 상태를 변경하지 않음
                System.out.println("Thread is running : " + Thread.currentThread().isInterrupted());
                if (Thread.interrupted()){
                    System.out.println("인터럽트 상태가 초기화");
                    break;
                }
            }
            System.out.println("Thread is interrupted");
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt(); // 여기서 인터럽트 상태를 원복하는 이유
            // 현재 스레드의 인터럽트 상태를 참조하고 있는 다른 스레드에서 인터럽트 상태가 true 일 때 동작을 시킨다고 하면
            // 인터럽트 상태를 초기화 하지 않으면 다른 스레드에서 현재 스레드의 상태를 참조하고 있기 때문에 동작하지 않는다.



            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
        });

        thread1.start();

        Thread.sleep(1000);
        thread1.interrupt();
    }
}
