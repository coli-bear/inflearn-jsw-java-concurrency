package section01.example01;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// 병렬처리 예제
public class ParallelismExample {

    public static void main(String[] args) {
        // CPU 코어 수 확인
        int cpuCores = Runtime.getRuntime().availableProcessors();

        // CPU Core 수 만큼 Integer Data 생성
        List<Integer> integers = IntStream.range(0, cpuCores).collect(ArrayList::new, List::add, List::addAll);

        // 병렬 처리를 위한 ParallelismFunction 생성
        final PowFunction parallelismFunction = new PowFunction();

        long startTime = System.currentTimeMillis();
        long sum = integers.parallelStream().mapToLong(parallelismFunction::apply).sum();

        long endTime = System.currentTimeMillis();
        System.out.println("[처리 결과]");
        System.out.println("Cpu Cores: " + cpuCores);
        System.out.println("Sum: " + sum + ", Time: " + (endTime - startTime) + "ms");

    }

}
