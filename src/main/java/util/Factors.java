package util;

import io.reactivex.rxjava3.core.Observable;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static util.Primes.primes;
import static util.Utils.upTo;

public class Factors {
    public static final String DATA_DIR = "data";
    public static final String FILE_BASE_NAME = "largest_";
    public static int[] largest = new int[0];
    public static boolean verbose = false;

    public static void makeTable(int n) {
        largest = new int[n];
        Arrays.fill(largest, 1);
        primes()
                .compose(upTo(n))
                .map(Long::intValue)
                .doOnNext(p -> {
                    for (int i = p; i < n; i += p) {
                        largest[i] = p;
                    }
                })
                .blockingSubscribe();

        File d = new File(DATA_DIR);
        if (!d.exists() && !d.mkdir()) {
            if (verbose) {
                System.err.println("Unable to create directory " + d.getAbsolutePath());
            }
            return;
        }
        File f = new File(DATA_DIR, FILE_BASE_NAME + n);
        try (FileOutputStream fos = new FileOutputStream(f);
             DataOutputStream dos = new DataOutputStream(fos)) {
            for (int p : largest) {
                dos.writeInt(p);
            }
            if (verbose) {
                System.out.println("Wrote to " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTable(int n) {
        largest = new int[n];
        File f = new File(DATA_DIR, FILE_BASE_NAME + n);
        try (FileInputStream fis = new FileInputStream(f);
             DataInputStream dis = new DataInputStream(fis)) {
            for (int i = 0; i < n; i++) {
                largest[i] = dis.readInt();
            }
            if (verbose) {
                System.out.println("Read from " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Observable<Long> factors(long n) {
        return Observable.generate(
                () -> new FactorsIterator(n),
                (factors, emitter) -> {
                    if (factors.hasNext()) {
                        emitter.onNext(factors.next());
                    } else {
                        emitter.onComplete();
                    }
                }
        );
    }

    public static class FactorsIterator implements Iterator<Long> {
        private long n;
        private long p;
        private final Iterator<Long> primes = new Primes.PrimesIterator();

        public FactorsIterator(long n) {
            this.n = n;
            this.p = primes.next();
        }

        @Override
        public boolean hasNext() {
            return n > 1;
        }

        @Override
        public Long next() {
            if (n <= 1) {
                throw new NoSuchElementException();
            }
            if (n < largest.length) {
                p = largest[(int) n];
            } else {
                while (n % p != 0) {
                    p = primes.next();
                }
            }
            n /= p;
            return p;
        }
    }

    public static void main(String[] args) {
        verbose = true;
        makeTable(1_000_000);
        verbose = false;
    }
}
