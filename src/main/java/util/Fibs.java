package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.Iterator;

public class Fibs {

    public static Observable<Long> fibs() {
        return Observable.generate(
                FibsIterator::new,
                (fibs, emitter) -> {
                    emitter.onNext(fibs.next());
                    return fibs;
                });
    }

    public static class FibsIterator implements Iterator<Long> {
        long a = 0;
        long b = 1;

        @Override
        public Long next() {
            long next = a;
            a = b;
            b = next + b;
            return next;
        }

        @Override
        public boolean hasNext() {
            return true;
        }
    }
}
