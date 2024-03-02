package section01.example03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBound {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() / 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long start = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            executorService.submit(new FileIOCallable(i)::run);
        }
        long end = System.currentTimeMillis();

        System.out.println("Time: " + (end - start) + "ms");
        executorService.shutdown();
    }
}

class FileIOCallable implements Runnable {
    private int threadNum;

    public FileIOCallable(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        // IO 집중 작업
        try {
            for (int i = 0; i < 5; i++) {
                Files.readAllLines(Path.of("/Users/geontae/workspace/study/book/java-concurrency-programming/src/main/java/section01/example03/ioboundedprocess"));
                System.out.println("Thread: " + Thread.currentThread().getName() + ", thread num: " + this.threadNum + ", i: " + i);
            }

            // 아주 빠른 CPU 연산
            int result = 0;
            for (int i = 0; i < 10; i++) {
                result += i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}