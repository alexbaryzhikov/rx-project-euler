package problems;

import io.reactivex.rxjava3.core.Observable;

public class Problem9 {
    public static void main(String[] args) {
        Observable.range(1, 1000)
                .flatMap(x -> Observable.range(x + 1, 1000)
                        .flatMap(y -> Observable.range(y + 1, 1000)
                                .filter(z -> x * x + y * y == z * z)
                                .filter(z -> x + y + z == 1000)
                                .map(z -> x * y * z)))
                .firstElement()
                .blockingSubscribe(System.out::println);
    }
}
