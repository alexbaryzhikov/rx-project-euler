package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Divisors {

    public static Observable<Long> divisors(long x) {
        return Observable.generate(
                () -> new DivisorsIterator(x),
                (divisors, emitter) -> {
                    if (divisors.hasNext()) {
                        emitter.onNext(divisors.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static class DivisorsIterator implements Iterator<Long> {
        private final long x;
        private final long sqrtX;
        private long i = 1;
        private long j = 0;

        public DivisorsIterator(long x) {
            this.x = x;
            this.sqrtX = (long) Math.sqrt(x);
        }

        @Override
        public boolean hasNext() {
            return i <= sqrtX || j != 0;
        }

        @Override
        public Long next() {
            if (i > sqrtX && j == 0) {
                throw new NoSuchElementException();
            }
            long result;
            if (j == 0) {
                result = i;
                j = x / i;
                if (j == x || j == i) {
                    j = 0;
                }
                do {
                    i++;
                } while (x % i != 0 && i <= sqrtX);
            } else {
                result = j;
                j = 0;
            }
            return result;
        }
    }
}
