package problems;

import io.reactivex.rxjava3.core.Observable;

/*
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

  a2 + b2 = c2

For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
*/

public class Problem9 {

    public static void main(String[] args) {
        Observable.range(1, 1000)
                .flatMap(x -> Observable.range(x + 1, 1000)
                        .flatMap(y -> Observable.range(y + 1, 1000)
                                .filter(z -> x * x + y * y == z * z)
                                .filter(z -> x + y + z == 1000)
                                .map(z -> x * y * z)))
                .firstElement()
                .blockingSubscribe(System.out::println);
    }
}
