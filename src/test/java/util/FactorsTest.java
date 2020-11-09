package util;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static util.Factors.*;

public class FactorsTest {

    @Test
    public void make_and_load_table() {
        makeTable(1000);
        int[] largest = Factors.largest;
        loadTable(1000);
        assertArrayEquals(largest, Factors.largest);
    }

    @Test
    public void generate_prime_factors_small() {
        List<Long> result = factors(720)
                .collect(ArrayList<Long>::new, ArrayList::add)
                .blockingGet();
        assertThat(result).containsExactly(5L, 3L, 3L, 2L, 2L, 2L, 2L);

        result = factors(37)
                .collect(ArrayList<Long>::new, ArrayList::add)
                .blockingGet();
        assertThat(result).containsExactly(37L);
    }

    @Test
    public void generate_prime_factors_large() {
        List<Long> result = factors(72_990_720)
                .distinct()
                .sorted()
                .collect(ArrayList<Long>::new, ArrayList::add)
                .blockingGet();
        assertThat(result).containsExactly(2L, 3L, 5L, 11L);
    }

    @AfterClass
    public static void after() {
        for (int i : Arrays.asList(1000, 1_000_001)) {
            File f = new File(DATA_DIR, FILE_BASE_NAME + i);
            if (f.delete()) {
                System.out.println("Deleted " + f.getAbsolutePath());
            } else {
                System.err.println("Unable to delete " + f.getAbsolutePath());
            }
        }
    }
}