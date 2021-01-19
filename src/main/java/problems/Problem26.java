package problems;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashMap;
import java.util.Map;

/*
A unit fraction contains 1 in the numerator. The decimal representation of the unit
fractions with denominators 2 to 10 are given:

1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1

Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle.
It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its
decimal fraction part.
*/

public class Problem26 {

    /**
     * Numeric solution (faster).
     */
    private static int findPeriodLength(int d) {
        while (d % 2 == 0) {
            d /= 2;
        }
        while (d % 5 == 0) {
            d /= 5;
        }
        int q = 9; // magic number 99...9
        int n = 1;
        while (q != 0) {
            q = (q * 10 + 9) % d;
            n++;
        }
        return n;
    }

    /**
     * Memory-based solution.
     */
    private static int findPeriodLength2(int d) {
        int r = 10;
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; ; i++) {
            if (r == 0) {
                return 0;
            }
            if (seen.containsKey(r)) {
                return i - seen.get(r);
            }
            seen.put(r, i);
            r = (r % d) * 10;
        }
    }

    public static void main(String[] args) {
        Observable.range(2, 998)
                .map(d -> new int[]{d, findPeriodLength(d)})
                .reduce(new int[]{0, 0}, (max, a) -> max[1] < a[1] ? a : max)
                .blockingSubscribe(a -> System.out.println(a[0]));
    }
}
