package problems;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

/*
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million.
*/

public class Problem10 {
    private static final List<Integer> primes = new ArrayList<>();

    private static boolean isPrime(int x) {
        for (int p : primes) {
            if (x % p == 0) return false;
        }
        primes.add(x);
        return true;
    }

    public static void main(String[] args) {
        Observable.<Integer>create(emitter -> {
            for (int i = 2; i < 2_000_000; i++) {
                emitter.onNext(i);
            }
            emitter.onComplete();
        })
                .filter(Problem10::isPrime)
                .map(it -> (long) it)
                .reduce(Long::sum)
                .blockingSubscribe(System.out::println);
    }
}
