package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.Iterator;

public class Fibs {

    public static Observable<Long> fibs() {
        return Observable.generate(
                FibsIterator::new,
                (fibs, emitter) -> {
                    emitter.onNext(fibs.next());
                });
    }

    public static class FibsIterator implements Iterator<Long> {
        private long a = 0;
        private long b = 1;

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
