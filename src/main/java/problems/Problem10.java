package problems;

import static util.Primes.primes;
import static util.Utils.upTo;

/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million.
*/

public class Problem10 {

    public static void main(String[] args) {
        primes().compose(upTo(2_000_000))
                .reduce(Long::sum)
                .blockingSubscribe(System.out::println);
    }
}
