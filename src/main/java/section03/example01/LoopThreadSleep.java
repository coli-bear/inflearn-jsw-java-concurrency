package section03.example01;

public class LoopThreadSleep {
    public static void main(String[] args) {
        for(int i = 0 ; i< 7 ; i++) {
            System.out.println("반속 : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
