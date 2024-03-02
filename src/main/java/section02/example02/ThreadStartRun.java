package section02.example02;

public class ThreadStartRun {
    public static void main(String[] args) {
        System.out.println("start thread :" + Thread.currentThread().getName());
        System.out.println("=====================================");
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("This is an anonymous thread : " + Thread.currentThread().getName());
            }
        };

        thread.start();
        thread.run();

        /*
Output: 처리 run 이 호출된 경우 main thread 의 이름이 찍히는 것을 확인할 수 있다.
start thread :main
=====================================
This is an anonymous thread : main
This is an anonymous thread : Thread-0
         */
    }
}
