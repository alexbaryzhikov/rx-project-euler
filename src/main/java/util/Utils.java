package util;

import com.alexb.iterators.*;
import com.alexb.iterators.date.Day;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableTransformer;

import java.util.Collection;
import java.util.List;
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

    public static Observable<Long> fibs() {
        return Observable.generate(
                FibsIterator::new,
                (fibs, emitter) -> {
                    emitter.onNext(fibs.next());
                });
    }

    public static Observable<Long> primes() {
        return Observable.generate(
                PrimesIterator::new,
                (primes, emitter) -> {
                    emitter.onNext(primes.next());
                }
        );
    }

    public static Observable<Day> days() {
        return Observable.generate(
                DaysIterator::new,
                (days, emitter) -> {
                    emitter.onNext(days.next());
                });
    }

    public static Observable<Long> divisors(long n) {
        return Observable.generate(
                () -> new DivisorsIterator(n),
                (divisors, emitter) -> {
                    if (divisors.hasNext()) {
                        emitter.onNext(divisors.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static Observable<Long> factors(long n) {
        return Observable.generate(
                () -> new FactorsIterator(n),
                (factors, emitter) -> {
                    if (factors.hasNext()) {
                        emitter.onNext(factors.next());
                    } else {
                        emitter.onComplete();
                    }
                }
        );
    }

    public static <T> Observable<List<T>> combinations(Collection<T> items, int k) {
        return Observable.generate(
                () -> new CombinationsIterator<>(items, k),
                (combinations, emitter) -> {
                    if (combinations.hasNext()) {
                        emitter.onNext(combinations.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static <T> Observable<List<T>> permutations(Collection<T> items, int k) {
        return Observable.generate(
                () -> new PermutationsIterator<>(items, k),
                (permutations, emitter) -> {
                    if (permutations.hasNext()) {
                        emitter.onNext(permutations.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static <T> Observable<List<T>> permutations(Collection<T> items) {
        return permutations(items, items.size());
    }

    public static ObservableTransformer<Long, Long> upTo(long n) {
        return upstream -> upstream.takeWhile(it -> it <= n);
    }

    public static ObservableTransformer<String, String> grep(String regex) {
        java.util.function.Predicate<String> p = Pattern.compile(regex).asPredicate();
        return upstream -> upstream.filter(p::test);
    }

    public static long sumOf(Observable<Long> o) {
        return o.reduce(0L, Long::sum).blockingGet();
    }

    public static long productOf(Observable<Long> o) {
        return o.reduce(1L, (a, b) -> a * b).blockingGet();
    }

    public static void timeIt(Runnable f) {
        long t0 = System.nanoTime();
        f.run();
        long t1 = System.nanoTime();
        long d = t1 - t0;
        System.out.printf("%d.%d s", d / 1000000000, (d % 1000000000) / 1000000);
    }
}
