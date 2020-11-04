package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Primes {

    public static Observable<Long> primes() {
        return Observable.generate(
                Sieve::new,
                (sieve, emitter) -> {
                    emitter.onNext(sieve.next());
                    return sieve;
                }
        );
    }

    private static class Sieve {
        long n = 2; // next candidate number
        long p = 3; // next base prime
        long q = 9; // square of the next base prime to keep track of in the sieve
        Map<Long, Long> sieve = new HashMap<>();
        List<Long> basePrimes = new ArrayList<>();

        Long next() {
            if (n < 4) {
                if (n == 2) {
                    n = 3;
                    return 2L;
                } else {
                    n = 5;
                    return 3L;
                }
            }
            while (true) {
                if (!sieve.containsKey(n)) {
                    if (n < q) {
                        // New prime found!
                        basePrimes.add(n);
                        n += 2;
                        return basePrimes.get(basePrimes.size() - 1);
                    } else {
                        // Add new entry to sieve and pull another base prime.
                        sieve.put(q + p * 2, p * 2);
                        p = basePrimes.remove(0);
                        q = p * p;
                    }
                } else {
                    // We hit the marked number, update the sieve.
                    long step = sieve.remove(n);
                    long next = n + step;
                    while (sieve.containsKey(next)) {
                        next += step;
                    }
                    sieve.put(next, step);
                }
                n += 2;
            }
        }
    }
}
