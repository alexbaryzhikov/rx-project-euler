package problems;

import io.reactivex.rxjava3.core.Observable;

import java.math.BigInteger;

import static util.Utils.nats;

public class Problem20 {

    public static void main(String[] args) {
        nats(1, 101)
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply)
                .flatMapObservable(it -> Observable.<Integer>create(emitter -> {
                    for (char c : it.toString().toCharArray()) {
                        emitter.onNext(c - '0');
                    }
                    emitter.onComplete();
                }))
                .reduce(0, Integer::sum)
                .blockingSubscribe(System.out::println);
    }
}
