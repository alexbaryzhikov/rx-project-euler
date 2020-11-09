package problems;

/*
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
*/

import io.reactivex.rxjava3.core.Observable;

import java.math.BigInteger;

public class Problem16 {

    public static void main(String[] args) {
        BigInteger x = BigInteger.valueOf(2).pow(1000);
        Observable.<Integer>create(emitter -> {
            for (char c : x.toString().toCharArray()) {
                emitter.onNext((int) c - '0');
            }
            emitter.onComplete();
        })
                .reduce(0, Integer::sum)
                .blockingSubscribe(System.out::println);
    }
}
