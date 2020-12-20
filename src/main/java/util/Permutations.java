package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations {

    public static <T> Observable<List<T>> permutations(List<T> items, int r) {
        return Observable.generate(
                () -> new PermutationsIterator<>(items, r),
                (permutations, emitter) -> {
                    if (permutations.hasNext()) {
                        emitter.onNext(permutations.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static <T> Observable<List<T>> permutations(List<T> items) {
        return permutations(items, items.size());
    }

    public static class PermutationsIterator<T> implements Iterator<List<T>> {
        private final List<T> items;
        private final int r;
        private final int n;
        private final int[] indices;
        private final int[] cycles;
        private int pivot;
        private List<T> permutation;

        public PermutationsIterator(List<T> items, int r) {
            this.items = items;
            this.r = r;
            n = items.size();
            indices = new int[n];
            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }
            cycles = new int[r];
            for (int i = 0; i < r; i++) {
                cycles[i] = n - i;
            }
            pivot = r - 1;
            if (!items.isEmpty() && r > 0) {
                permutation = items.subList(0, r);
            }
        }

        @Override
        public boolean hasNext() {
            return permutation != null;
        }

        @Override
        public List<T> next() {
            if (permutation == null) {
                throw new NoSuchElementException();
            }
            List<T> result = permutation;
            permutation = null;

            for (; pivot >= 0; pivot--) {
                cycles[pivot] -= 1;
                if (cycles[pivot] == 0) {
                    rotateLeft(indices, pivot, n - 1);
                    cycles[pivot] = n - pivot;
                } else {
                    swap(indices, pivot, n - cycles[pivot]);
                    pivot = r - 1;
                    permutation = new ArrayList<>();
                    for (int i = 0; i < r; i++) {
                        permutation.add(items.get(indices[i]));
                    }
                    break;
                }
            }

            return result;
        }

        private static void rotateLeft(int[] array, int startPos, int endPos) {
            if (startPos == endPos) {
                return;
            }
            int x = array[startPos];
            System.arraycopy(array, startPos + 1, array, startPos, endPos - startPos);
            array[endPos] = x;
        }

        private static void swap(int[] array, int i, int j) {
            int x = array[i];
            array[i] = array[j];
            array[j] = x;
        }
    }
}
