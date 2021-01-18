package problems;

import io.reactivex.rxjava3.core.Observable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Problem26 {

    private static int findCycleLength(String s) {
        List<int[]> cycles = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(0) == s.charAt(i)) {
                cycles.add(new int[]{i, 0});
            }
            List<int[]> newCycles = new ArrayList<>();
            for (int[] cycle : cycles) {
                if (s.charAt(cycle[1]) != s.charAt(i)) {
                    continue;
                }
                cycle[1] = (cycle[1] + 1) % cycle[0];
                // Heuristic: pattern repeats at least 3 times.
                if (cycle[1] == 0 && i > cycle[0] * 2) {
                    return cycle[0];
                }
                newCycles.add(cycle);
            }
            cycles = newCycles;
        }
        return cycles.isEmpty() || cycles.get(0)[0] > s.length() / 2 ? 0 : cycles.get(0)[0];
    }

    public static void main(String[] args) {
        Observable.range(2, 998)
                .map(d -> {
                    BigDecimal decimal = BigDecimal.ONE.divide(new BigDecimal(d), 2000, RoundingMode.DOWN);
                    int cycleLength = findCycleLength(decimal.toPlainString().substring(2));
                    return new int[]{d, cycleLength};
                })
                .reduce(new int[]{0, 0}, (max, a) -> max[1] < a[1] ? a : max)
                .map(a -> a[0])
                .blockingSubscribe(System.out::println);
    }
}
