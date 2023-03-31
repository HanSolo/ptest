package eu.hansolo.ptest;

import java.util.stream.LongStream;


public class Main {
    private static final long[] VALUES = { 1_000l, 10_000l, 100_000l, 1_000_000l, 10_000_000l, 100_000_000l, 1_000_000_000l };

    public static void main(String[] args) {
        System.out.println("Benchmarking serial vs parallel stream ( filter(p -> p % 2 == 0) )");

        for (long value : VALUES) {
            final long t0 = System.nanoTime();

            final long a[] = LongStream.range(0, value).filter(p -> p % 2 == 0).toArray();
            final long t1  = System.nanoTime();

            final long b[] = LongStream.range(0, value).parallel().filter(p -> p % 2 == 0).toArray();
            final long t2  = System.nanoTime();

            System.out.printf("serial: %.2fs, parallel %.2fs -> %,d%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9, value);
        }
    }
}