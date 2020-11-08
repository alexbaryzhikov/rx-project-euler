package util;

import org.junit.Before;
import org.junit.Test;
import problems.*;

import static org.junit.Assert.assertEquals;


public class ProblemsTest {
    private final StdOutInterceptor stdOutInterceptor = new StdOutInterceptor();

    @Before
    public void before() {
        stdOutInterceptor.start();
    }

    @Test
    public void problem1() {
        Problem1.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("233168", result);
    }

    @Test
    public void problem8() {
        Problem8.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("23514624000", result);
    }

    @Test
    public void problem9() {
        Problem9.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("31875000", result);
    }

    @Test
    public void problem10() {
        Problem10.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("142913828922", result);
    }

    @Test
    public void problem11() {
        Problem11.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("70600674", result);
    }

    @Test
    public void problem12() {
        Problem12.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("76576500", result);
    }

    @Test
    public void problem13() {
        Problem13.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("5537376230", result);
    }

    @Test
    public void problem14() {
        Problem14.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("837799", result);
    }

    @Test
    public void problem15() {
        Problem15.main(null);
        String result = stripNewline(stdOutInterceptor.capture());
        assertEquals("137846528820", result);
    }

    private String stripNewline(String s) {
        return s.replace(System.lineSeparator(), "");
    }
}
