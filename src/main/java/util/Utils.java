package util;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableConverter;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Single;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Utils {

    public static Observable<Long> nats(long from) {
        return Observable.generate(
                () -> from,
                (i, emitter) -> {
                    emitter.onNext(i);
                    return i + 1;
                });
    }

    public static Observable<Long> nats(long from, long to) {
        return Observable.generate(
                () -> from,
                (i, emitter) -> {
                    if (i < to) {
                        emitter.onNext(i);
                        return i + 1;
                    } else {
                        emitter.onComplete();
                        return 0L;
                    }
                });
    }

    public static ObservableTransformer<Long, Long> upTo(long n) {
        return upstream -> upstream.takeWhile(it -> it <= n);
    }

    public static ObservableConverter<Long, Single<Long>> product() {
        return upstream -> upstream.reduce(1L, (a, b) -> a * b);
    }

    public static ObservableTransformer<String, String> grep(String regex) {
        Predicate<String> p = Pattern.compile(regex).asPredicate();
        return upstream -> upstream.filter(p::test);
    }
}
