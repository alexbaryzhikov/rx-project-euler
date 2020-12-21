package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.*;

public class Combinations {

    public static <T> Observable<List<T>> combinations(Collection<T> items, int r) {
        return Observable.generate(
                () -> new CombinationsIterator<>(items, r),
                (combinations, emitter) -> {
                    if (combinations.hasNext()) {
                        emitter.onNext(combinations.next());
                    } else {
                        emitter.onComplete();
                    }
                });
    }

    public static <T> Observable<List<T>> combinations(Collection<T> items) {
        return combinations(items, items.size());
    }

    public static class CombinationsIterator<T> implements Iterator<List<T>> {
        private final T[] items;
        private final int r;
        private final int n;
        private final int[] indices;
        private List<T> combination;

        @SuppressWarnings({"unchecked"})
        public CombinationsIterator(Collection<T> items, int r) {
            this.items = (T[]) items.toArray();
            this.r = r;
            n = items.size();
            indices = new int[r];
            for (int i = 0; i < r; i++) {
                indices[i] = i;
            }
            if (!items.isEmpty() && r > 0 && r <= n) {
                combination = getCombination();
            }
        }

        private List<T> getCombination() {
            List<T> result = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                result.add(items[indices[i]]);
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return combination != null;
        }

        @Override
        public List<T> next() {
            if (combination == null) {
                throw new NoSuchElementException();
            }
            List<T> result = combination;
            combination = null;

            int i = r - 1;
            for (; i >= 0; i--) {
                if (indices[i] != i + n - r) {
                    break;
                }
            }

            if (i >= 0) {
                indices[i]++;
                for (int j = i + 1; j < r; j++) {
                    indices[j] = indices[j - 1] + 1;
                }
                combination = getCombination();
            }

            return result;
        }
    }
}
