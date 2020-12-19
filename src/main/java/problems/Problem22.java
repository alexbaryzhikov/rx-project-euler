package problems;

import io.reactivex.rxjava3.core.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem22 {

    private static List<String> getNames() {
        InputStream is = ClassLoader.getSystemResourceAsStream("p022_names.txt");
        if (is == null) return new ArrayList<>();
        String line;
        try {
            line = new BufferedReader(new InputStreamReader(is)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] names = line.replace("\"", "").split(",");
        return Arrays.asList(names);
    }

    public static void main(String[] args) {
        List<String> names = getNames();
        names.sort(Comparator.naturalOrder());
        Observable.fromIterable(names)
                .flatMapSingle(name -> Observable.fromStream(name.chars().boxed())
                        .reduce(0, (sum, c) -> sum + c - 'A' + 1))
                .zipWith(Observable.range(1, names.size()), (i, j) -> i * j)
                .reduce(0, Integer::sum)
                .blockingSubscribe(System.out::println);
    }
}
