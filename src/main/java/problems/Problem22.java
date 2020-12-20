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

/*
Using names.txt (right click and 'Save Link/Target As...'), a 46K text file
containing over five-thousand first names, begin by sorting it into alphabetical
order. Then working out the alphabetical value for each name, multiply this
value by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is
worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would
obtain a score of 938 × 53 = 49714.

What is the total of all the name scores in the file?
*/

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
