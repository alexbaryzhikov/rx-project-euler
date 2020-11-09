package problems;

/*
2520 is the smallest number that can be divided by each
of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly
divisible by all of the numbers from 1 to 20?
*/

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableConverter;
import io.reactivex.rxjava3.core.Single;

import java.util.HashMap;
import java.util.Map;

import static util.Factors.factors;
import static util.Utils.*;

public class Problem5 {

    public static void main(String[] args) {
        nats(2).compose(upTo(20))
                .flatMapSingle(it -> factors(it).to(frequencies()))
                .to(highestFrequencies())
                .flatMapObservable(it -> Observable.fromIterable(it.entrySet()))
                .reduce(1L, (acc, it) -> acc * (long) Math.pow(it.getKey(), it.getValue()))
                .blockingSubscribe(System.out::println);
    }

    private static ObservableConverter<Long, Single<Map<Long, Integer>>> frequencies() {
        return upstream -> upstream.collect(
                HashMap<Long, Integer>::new,
                (acc, x) -> {
                    if (acc.containsKey(x)) {
                        acc.put(x, acc.get(x) + 1);
                    } else {
                        acc.put(x, 1);
                    }
                }
        );
    }

    private static ObservableConverter<Map<Long, Integer>, Single<Map<Long, Integer>>> highestFrequencies() {
        return upstream -> upstream.collect(
                HashMap<Long, Integer>::new,
                (acc, m) -> {
                    for (Long x : m.keySet()) {
                        if (acc.containsKey(x)) {
                            acc.put(x, Math.max(acc.get(x), m.get(x)));
                        } else {
                            acc.put(x, m.get(x));
                        }
                    }
                }
        );
    }
}
