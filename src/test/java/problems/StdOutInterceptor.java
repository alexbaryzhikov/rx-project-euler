package problems;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StdOutInterceptor {
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream baos;

    public void start() {
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    public String capture() {
        System.setOut(systemOut);
        return baos.toString();
    }
}
