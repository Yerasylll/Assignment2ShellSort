package algorithms;

import com.company.algorithms.ShellSort;
import com.company.metricsAndCsv.metrics.MetricsCollector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ShellSortTest {

    private int[] copyArray(int[] arr) { return Arrays.copyOf(arr, arr.length); }

    private void assertSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i - 1] <= arr[i], "Array not sorted at index " + i);
        }
    }

    @Test
    void testShellSortVariantsSmallInput() {
        int[] input = {5, 2, 9, 1, 5, 6};
        for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
            int[] arr = copyArray(input);
            MetricsCollector metrics = new MetricsCollector();
            ShellSort.sort(arr, metrics, seq);

            assertSorted(arr);
            assertTrue(metrics.comparisons().getComparisons() > 0);
            assertTrue(metrics.time().getElapsedTime() >= 0);

            System.out.printf("ShellSort (%s), n=%d: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                    seq,
                    arr.length,
                    metrics.comparisons().getComparisons(),
                    metrics.swaps().getSwaps(),
                    metrics.accesses().getAccesses(),
                    metrics.time().getElapsedTime()
            );
        }
    }

    @Test
    void testShellSortLargeRandomInput() {
        Random rand = new Random(42);
        int n = 10_000;
        int[] input = rand.ints(n, -10000, 10000).toArray();

        for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
            int[] arr = copyArray(input);
            MetricsCollector metrics = new MetricsCollector();
            ShellSort.sort(arr, metrics, seq);

            assertSorted(arr);
            assertTrue(metrics.time().getElapsedTime() > 0);

            System.out.printf("ShellSort (%s), n=%d: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                    seq,
                    arr.length,
                    metrics.comparisons().getComparisons(),
                    metrics.swaps().getSwaps(),
                    metrics.accesses().getAccesses(),
                    metrics.time().getElapsedTime()
            );
        }
    }

    @Test
    void testShellSortEdgeCases() {
        MetricsCollector metrics = new MetricsCollector();

        int[] empty = {};
        ShellSort.sort(empty, metrics, ShellSort.GapSequence.SHELL);
        assertEquals(0, empty.length);

        int[] single = {42};
        ShellSort.sort(single, metrics, ShellSort.GapSequence.KNUTH);
        assertArrayEquals(new int[]{42}, single);

        int[] duplicates = {7, 7, 7, 7};
        ShellSort.sort(duplicates, metrics, ShellSort.GapSequence.SEDGEWICK);
        assertArrayEquals(new int[]{7, 7, 7, 7}, duplicates);

        System.out.printf("ShellSort edge cases: time=%.3f ms%n", metrics.time().getElapsedTime());
    }
}
