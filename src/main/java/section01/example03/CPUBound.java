package section01.example03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBound {
    public static void main(String[] args) {
        // CPU 코어 수 확인
        int numThreads = Runtime.getRuntime().availableProcessors();
        // Thread 생성기
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long start = System.currentTimeMillis();
        List<Future<?>> future = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Future<?> submit = executorService.submit(new CPURunnable());
            future.add(submit);
        }

        for (Future<?> future1 : future) {
            try {
                future1.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        executorService.shutdown();
    }
}

class CPURunnable implements Runnable {

    @Override
    public void run()  {
        long result = 0;
        // CPU 연산이 집중되고 오래 걸리는 작업
        for (int i = 0; i < 100000000L; i++) {
            result += i;
        }

        try {
            // 잠시 대기
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread: " + Thread.currentThread().getName() + ", result: " + result);
    }
}
