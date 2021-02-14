package problems;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;
import java.util.Set;

import static util.Utils.primes;

/*
Euler discovered the remarkable quadratic formula:

  n^2 + n + 41

It turns out that the formula will produce 40 primes for the consecutive integer
values 0 <= n <= 39. However, when n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41
is divisible by 41, and certainly when n = 41, 41^2 + 41 + 41 is clearly divisible by 41.

The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes for the
consecutive values 0 <= n <= 79. The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

  n^2 + an + b, where |a| < 1000 and |b| <= 1000

Find the product of the coefficients, a and b, for the quadratic expression that produces
the maximum number of primes for consecutive values of n, starting with n = 0.
*/

public class Problem27 {
    private static Set<Long> primesSet;

    private static long[] primesCount(long[] ab) {
        for (long n = 0; ; n++) {
            long x = n * n + n * ab[0] + ab[1];
            if (!primesSet.contains(x)) {
                return new long[]{ab[0], ab[1], n};
            }
        }
    }

    public static void main(String[] args) {
        primesSet = primes()
                .takeWhile(n -> n < 100_000)
                .collect(HashSet<Long>::new, HashSet::add)
                .blockingGet();

        Observable.range(-999, 2000 - 1)
                .flatMap(a -> Observable.rangeLong(-1000, 2000 + 1)
                        .map(b -> new long[]{a, b}))
                .map(Problem27::primesCount)
                .reduce(new long[]{0, 0, 0}, (abnMax, abn) -> abn[2] > abnMax[2] ? abn : abnMax)
                .map(abn -> abn[0] * abn[1])
                .blockingSubscribe(System.out::println);
    }
}
