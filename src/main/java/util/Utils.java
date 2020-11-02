package util;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static final DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:ss.SSS");

    public static <T> void log(String tag, T item) {
        System.out.printf(
                "%s [%s] %s %s%n",
                LocalTime.now().format(f),
                Thread.currentThread().getName(),
                tag,
                item
        );
    }

    public static void sleep(long duration) {
        try {
            TimeUnit.MILLISECONDS.sleep(duration);
        } catch (InterruptedException ignored) {
        }
    }

    public static int randomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    public static long randomLong(int from, int to) {
        return ThreadLocalRandom.current().nextLong(from, to);
    }

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
}
