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

import static util.Utils.nats;

public class Problem14 {

    private static long collatzSize(long x) {
        long size = 1;
        while (x != 1) {
            x = x % 2 == 0 ? x / 2 : x * 3 + 1;
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        nats(2, 1_000_000)
                .reduce(new long[]{0, 0}, (acc, x) -> {
                    long sz = collatzSize(x);
                    if (acc[0] < sz) {
                        acc[0] = sz;
                        acc[1] = x;
                    }
                    return acc;
                })
                .map(a -> a[1])
                .blockingSubscribe(System.out::println);
    }
}
