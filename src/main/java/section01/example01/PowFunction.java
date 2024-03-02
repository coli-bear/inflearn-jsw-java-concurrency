package section01.example01;

import java.util.function.Function;

public class PowFunction implements Function<Integer, Long> {

    @Override
    public Long apply(Integer integer) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return integer.longValue() * integer.longValue();
    }
}
