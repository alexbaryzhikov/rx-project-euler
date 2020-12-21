package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static util.Combinations.combinations;

public class CombinationsTest {

    @Test
    public void combinations_of_empty_list() {
        List<List<Integer>> result = combinations(Collections.<Integer>emptyList())
                .toList().blockingGet();
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void combinations_of_size_0() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3), 0)
                .toList().blockingGet();
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void combinations_of_size_1() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3), 1)
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(0),
                Collections.singletonList(1),
                Collections.singletonList(2),
                Collections.singletonList(3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_2() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3), 2)
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(0, 3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_3() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3), 3)
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(0, 1, 3),
                Arrays.asList(0, 2, 3),
                Arrays.asList(1, 2, 3));
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_4() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3))
                .toList().blockingGet();
        List<List<Integer>> expected = Collections.singletonList(
                Arrays.asList(0, 1, 2, 3)
        );
        assertEquals(expected, result);
    }

    @Test
    public void combinations_of_size_5() {
        List<List<Integer>> result = combinations(Arrays.asList(0, 1, 2, 3), 5)
                .toList().blockingGet();
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }
}