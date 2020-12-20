package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static util.Permutations.permutations;

public class PermutationsTest {

    @Test
    public void permutations_of_empty_list() {
        List<List<Integer>> result = permutations(Collections.<Integer>emptyList())
                .toList().blockingGet();
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void permutations_of_size_0() {
        List<List<Integer>> result = permutations(Arrays.asList(0, 1, 2), 0)
                .toList().blockingGet();
        assertEquals(Collections.<List<Integer>>emptyList(), result);
    }

    @Test
    public void permutations_of_size_1() {
        List<List<Integer>> result = permutations(Arrays.asList(0, 1, 2), 1)
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Collections.singletonList(0),
                Collections.singletonList(1),
                Collections.singletonList(2));
        assertEquals(expected, result);
    }

    @Test
    public void permutations_of_size_2() {
        List<List<Integer>> result = permutations(Arrays.asList(0, 1, 2), 2)
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(0, 2),
                Arrays.asList(1, 0),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(2, 1));
        assertEquals(expected, result);
    }

    @Test
    public void permutations_of_size_3() {
        List<List<Integer>> result = permutations(Arrays.asList(0, 1, 2))
                .toList().blockingGet();
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(0, 2, 1),
                Arrays.asList(1, 0, 2),
                Arrays.asList(1, 2, 0),
                Arrays.asList(2, 0, 1),
                Arrays.asList(2, 1, 0));
        assertEquals(expected, result);
    }
}