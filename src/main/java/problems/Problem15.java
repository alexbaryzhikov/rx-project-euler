package problems;

import io.reactivex.rxjava3.core.Observable;

/*
Starting in the top left corner of a 2×2 grid, and only being able to move
to the right and down, there are exactly 6 routes to the bottom right corner.
How many such routes are there through a 20×20 grid?
*/
public class Problem15 {

    private static long comb(int n, int k) {
        int[] a = k < n - k ? new int[]{n - k, k} : new int[]{k, n - k};
        return Observable.zip(
                Observable.range(a[0] + 1, n - a[0]),
                Observable.range(1, a[1]),
                (i, j) -> new long[]{i, j})
                .reduce(1L, (x, p) -> x * p[0] / p[1])
                .blockingGet();
    }

    public static void main(String[] args) {
        System.out.println(comb(40, 20));
    }
}
