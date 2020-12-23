package problems;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

import static util.Utils.*;

/*
A perfect number is a number for which the sum of its proper divisors is
exactly equal to the number. For example, the sum of the proper divisors
of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect
number.

A number n is called deficient if the sum of its proper divisors is less
than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16,
the smallest number that can be written as the sum of two abundant
numbers is 24. By mathematical analysis, it can be shown that all
integers greater than 28123 can be written as the sum of two abundant
numbers. However, this upper limit cannot be reduced any further by
analysis even though it is known that the greatest number that cannot
be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as
the sum of two abundant numbers.
*/

public class Problem23 {

    public static void main(String[] args) {
        int threshold = 28124;

        List<Long> abundant = nats(12, threshold)
                .filter(it -> sumOf(divisors(it)) > it)
                .toList().blockingGet();

        Observable<Long> pairwise = Observable.fromIterable(abundant)
                .flatMap(a -> Observable.fromIterable(abundant)
                        .takeWhile(b -> b <= a && a + b < threshold)
                        .map(b -> a + b))
                .distinct();

        System.out.println(sumOf(nats(1, threshold)) - sumOf(pairwise));
    }
}
