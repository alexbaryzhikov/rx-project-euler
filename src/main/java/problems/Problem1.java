package problems;

import io.reactivex.rxjava3.core.Observable;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23. Find the sum of
 * all the multiples of 3 or 5 below 1000.
 */
public class Problem1 {

    private static Observable<Integer> divOf(int x, int limit) {
        return Observable.generate(() -> 1, (i, emitter) -> {
            int next = i + x;
            if (next < limit) {
                emitter.onNext(next);
            } else {
                emitter.onComplete();
            }
            return next;
        });
    }

    public static void main(String[] args) {
        Observable.merge(divOf(3, 1000), divOf(5, 1000))
                .distinct()
                .reduce(0, Integer::sum)
                .blockingSubscribe(System.out::println);
    }
}
