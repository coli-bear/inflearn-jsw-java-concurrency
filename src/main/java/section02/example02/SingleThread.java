package section02.example02;

public class SingleThread {
    public static void main(String[] args) {

        int sum= 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("sum = " + sum);

        System.out.println("메인 스레드 종료");
    }
}