package sandbox;

import io.reactivex.rxjava3.core.Observable;

public class GenerateNats {
    public static void main(String[] args) {
        Observable.create(
                emitter -> {
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        emitter.onNext(i++);
                    }
                    emitter.onComplete();
                })
                .take(10)
                .blockingSubscribe(System.out::print);

        Observable.generate(
                () -> 0,
                (i, emitter) -> {
                    emitter.onNext(i);
                    return i + 1;
                })
                .take(10)
                .blockingSubscribe(System.out::print);

        Observable.just(0).repeat().scan(0, (i, z) -> i + 1)
                .take(10)
                .blockingSubscribe(System.out::print);

        Observable.range(0, Integer.MAX_VALUE)
                .take(10)
                .blockingSubscribe(System.out::print);
    }
}
