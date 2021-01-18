package problems;

/*
What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
*/

import io.reactivex.rxjava3.core.Observable;

import java.math.BigInteger;

public class Problem25 {
    public static void main(String[] args) {
        Observable.generate(
                () -> new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},
                (a, emitter) -> {
                    emitter.onNext(a[1]);
                    BigInteger x = a[0];
                    a[0] = a[1];
                    a[1] = a[1].add(x);
                })
                .takeUntil(i -> i.toString().length() >= 1000)
                .count()
                .blockingSubscribe(System.out::println);
    }
}