package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.*;

public class Primes {

    public static Observable<Long> primes() {
        return Observable.generate(
                PrimesIterator::new,
                (primes, emitter) -> {
                    emitter.onNext(primes.next());
                }
        );
    }

    public static class PrimesIterator implements Iterator<Long> {
        private long n = 2; // next candidate number
        private long p = 3; // next base prime
        private long q = 9; // square of the next base prime to keep track of in the sieve
        private final Map<Long, Long> sieve = new HashMap<>();
        private final List<Long> basePrimes = new ArrayList<>();

        @Override
        public Long next() {
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

        @Override
        public boolean hasNext() {
            return true;
        }
    }
}
