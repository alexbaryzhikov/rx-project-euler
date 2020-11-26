package problems;

/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide
evenly into n). If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair
and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.
*/

import static util.Divisors.divisors;
import static util.Utils.nats;

public class Problem21 {

    public static void main(String[] args) {
        nats(1, 10000)
                .filter(a -> {
                    long dA = divisors(a).reduce(0L, Long::sum).blockingGet();
                    if (a == dA) return false;
                    long ddA = divisors(dA).reduce(0L, Long::sum).blockingGet();
                    return a == ddA;
                })
                .reduce(0L, Long::sum)
                .blockingSubscribe(System.out::println);
    }
}
