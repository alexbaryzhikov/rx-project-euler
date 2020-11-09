package problems;

/*
The sum of the squares of the first ten natural numbers is 385.
The square of the sum of the first ten natural numbers is 3025.
Hence the difference between the sum of the squares of the first
ten natural numbers and the square of the sum is 3025 - 385 = 2640.

Find the difference between the sum of the squares of the first
one hundred natural numbers and the square of the sum.
*/

import static util.Utils.nats;

public class Problem6 {

    public static void main(String[] args) {
        nats(1, 101)
                .reduce(new long[]{0, 0}, (acc, x) -> {
                    acc[0] += x;
                    acc[1] += x * x;
                    return acc;
                })
                .map(it -> it[0] * it[0] - it[1])
                .blockingSubscribe(System.out::println);
    }
}
