package sandbox;

import io.reactivex.rxjava3.core.Observable;

import static util.Primes.primes;
import static util.Utils.*;

public class UseUtils {

    public static void main(String[] args) {

        // Factorial of 5
        nats(1, 6)
                .to(product())
                .blockingSubscribe(System.out::println);

        // Grep
        Observable.just("Alpha", "Beta", "Gamma", "Delta")
                .compose(grep("ta"))
                .blockingSubscribe(System.out::println);

        // Largest prime up to boundary
        primes()
                .compose(upTo(2_000_000))
                .lastElement()
                .blockingSubscribe(System.out::println);
    }
}
