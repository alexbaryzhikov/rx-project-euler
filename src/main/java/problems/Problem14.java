package problems;

/*
The following iterative sequence is defined for the set of positive integers:

  n → n/2 (n is even)
  n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

  13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1

It can be seen that this sequence (starting at 13 and finishing at 1) contains
10 terms. Although it has not been proved yet (Collatz Problem), it is thought
that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
*/

import io.reactivex.rxjava3.core.Observable;

import static util.Utils.nats;

public class Problem14 {

    private static Observable<Long> collatz(long x) {
        return Observable.generate(
                () -> x,
                (i, emitter) -> {
                    emitter.onNext(i);
                    if (i != 1) {
                        return i % 2 == 0 ? i / 2 : i * 3 + 1;
                    } else {
                        emitter.onComplete();
                        return 0L;
                    }
                });
    }

    public static void main(String[] args) {
        nats(13, 1_000_000)
                .flatMapSingle(x -> collatz(x).count().map(n -> new long[]{x, n}))
                .reduce(new long[]{0, 0}, (max, item) -> max[1] < item[1] ? item : max)
                .map(a -> a[0])
                .blockingSubscribe(System.out::println);
    }
}
