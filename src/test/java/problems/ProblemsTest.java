package problems;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class ProblemsTest {
    private final StdOutInterceptor stdOutInterceptor = new StdOutInterceptor();

    @Before
    public void before() {
        stdOutInterceptor.start();
    }

    @Test
    public void problem1() {
        test(Problem1::main, "233168");
    }

    @Test
    public void problem2() {
        test(Problem2::main, "4613732");
    }

    @Test
    public void problem3() {
        test(Problem3::main, "6857");
    }

    @Test
    public void problem4() {
        test(Problem4::main, "906609");
    }

    @Test
    public void problem5() {
        test(Problem5::main, "232792560");
    }

    @Test
    public void problem6() {
        test(Problem6::main, "25164150");
    }

    @Test
    public void problem7() {
        test(Problem7::main, "104743");
    }

    @Test
    public void problem8() {
        test(Problem8::main, "23514624000");
    }

    @Test
    public void problem9() {
        test(Problem9::main, "31875000");
    }

    @Test
    public void problem10() {
        test(Problem10::main, "142913828922");
    }

    @Test
    public void problem11() {
        test(Problem11::main, "70600674");
    }

    @Test
    public void problem12() {
        test(Problem12::main, "76576500");
    }

    @Test
    public void problem13() {
        test(Problem13::main, "5537376230");
    }

    @Test
    public void problem14() {
        test(Problem14::main, "837799");
    }

    @Test
    public void problem15() {
        test(Problem15::main, "137846528820");
    }

    @Test
    public void problem16() {
        test(Problem16::main, "1366");
    }

    @Test
    public void problem17() {
        test(Problem17::main, "21124");
    }

    @Test
    public void problem18() {
        test(Problem18::main, "1074");
    }

    @Test
    public void problem19() {
        test(Problem19::main, "171");
    }

    @Test
    public void problem20() {
        test(Problem20::main, "648");
    }

    @Test
    public void problem21() {
        test(Problem21::main, "31626");
    }

    @Test
    public void problem22() {
        test(Problem22::main, "871198282");
    }

    @Test
    public void problem23() {
        test(Problem23::main, "4179871");
    }

    @Test
    public void problem24() {
        test(Problem24::main, "2783915460");
    }

    @Test
    public void problem25() {
        test(Problem25::main, "4782");
    }

    @Test
    public void problem26() {
        test(Problem26::main, "983");
    }

    private void test(Consumer<String[]> f, String expected) {
        f.accept(null);
        String result = stdOutInterceptor.capture().replace(System.lineSeparator(), "");
        assertEquals(expected, result);
    }
}
