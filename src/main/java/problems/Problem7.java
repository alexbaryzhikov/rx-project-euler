package problems;

/*
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
we can see that the 6th prime is 13.

What is the 10001st prime number?
*/

import static util.Primes.primes;

public class Problem7 {

    public static void main(String[] args) {
        primes().skip(10_000)
                .firstElement()
                .blockingSubscribe(System.out::println);
    }
}
