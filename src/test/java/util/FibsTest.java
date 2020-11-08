package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static util.Fibs.fibs;
import static util.Utils.upTo;

public class FibsTest {
    private static final String FIBS = "0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 " +
            "2584 4181 6765 10946 17711 28657 46368 75025 121393 196418 317811 ";

    @Test
    public void generate_fibs() {
        String result = fibs()
                .compose(upTo(317811))
                .collect(StringBuilder::new, (sb, i) -> sb.append(i).append(' '))
                .blockingGet().toString();
        assertEquals(FIBS, result);
    }
}