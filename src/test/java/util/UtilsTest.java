package util;

import io.reactivex.rxjava3.core.Observable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static util.Primes.primes;
import static util.Utils.*;

public class UtilsTest {

    @Test
    public void just_nats() {
        List<Long> result = nats(1, 6)
                .collect(ArrayList<Long>::new, ArrayList::add)
                .blockingGet();
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L), result);
    }

    @Test
    public void factorial() {
        long result = nats(1, 6).to(product()).blockingGet();
        assertEquals(120L, result);
    }

    @Test
    public void grep_pattern() {
        List<String> result = Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .compose(grep("ta"))
                .collect(ArrayList<String>::new, ArrayList::add)
                .blockingGet();
        assertEquals(Arrays.asList("Beta", "Delta"), result);
    }

    @Test
    public void largest_prime_up_to_boundary() {
        long result = primes()
                .compose(upTo(2_000_000))
                .lastOrError()
                .blockingGet();
        assertEquals(1999993L, result);
    }
}