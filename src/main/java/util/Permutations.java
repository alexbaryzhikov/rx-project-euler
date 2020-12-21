package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.*;

public class Permutations {

    public static <T> Observable<List<T>> permutations(Collection<T> items, int r) {
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

    public static <T> Observable<List<T>> permutations(Collection<T> items) {
        return permutations(items, items.size());
    }

    public static class PermutationsIterator<T> implements Iterator<List<T>> {
        private final T[] items;
        private final int r;
        private final int n;
        private final int[] indices;
        private final int[] cycles;
        private int k;
        private List<T> permutation;

        @SuppressWarnings("unchecked")
        public PermutationsIterator(Collection<T> items, int r) {
            this.items = (T[]) items.toArray();
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
            k = r - 1;
            if (!items.isEmpty() && r > 0 && r <= n) {
                permutation = getPermutation();
            }
        }

        private List<T> getPermutation() {
            List<T> result = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                result.add(items[indices[i]]);
            }
            return result;
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

            for (; k >= 0; k--) {
                cycles[k] -= 1;
                if (cycles[k] == 0) {
                    rotateLeft(indices, k, n - 1);
                    cycles[k] = n - k;
                } else {
                    swap(indices, k, n - cycles[k]);
                    k = r - 1;
                    permutation = getPermutation();
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
