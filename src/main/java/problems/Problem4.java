package problems;

/*
A palindromic number reads the same both ways. The largest palindrome made
from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
*/

import static util.Utils.nats;

public class Problem4 {

    private static long reverse(long x) {
        long y = 0;
        while (x > 0) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }

    public static void main(String[] args) {
        nats(100, 1000)
                .flatMap(x -> nats(x, 1000)
                        .map(y -> x * y))
                .filter(it -> it == reverse(it))
                .reduce(0L, Long::max)
                .blockingSubscribe(System.out::println);
    }
}
