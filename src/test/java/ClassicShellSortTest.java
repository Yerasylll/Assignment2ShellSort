import com.company.algorithms.ClassicShellSort;
import com.company.metricsAndCsv.metrics.MetricsCollector;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicShellSortTest {

    @Test
    void testClassicShellSortSmallInput() {
        MetricsCollector metrics = new MetricsCollector();
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        ClassicShellSort.sort(arr, metrics);

        System.out.printf("ClassicShellSort small input: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime());

        assertArrayEquals(expected, arr);
        assertTrue(metrics.time().getElapsedTime() > 0, "No time recorded");
    }

    @Test
    void testClassicShellSortAlreadySorted() {
        MetricsCollector metrics = new MetricsCollector();
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();

        ClassicShellSort.sort(arr, metrics);

        System.out.printf("ClassicShellSort already sorted: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime());

        assertArrayEquals(expected, arr);
    }

    @Test
    void testClassicShellSortReverseSorted() {
        MetricsCollector metrics = new MetricsCollector();
        int[] arr = {9, 7, 5, 3, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        ClassicShellSort.sort(arr, metrics);

        System.out.printf("ClassicShellSort reverse sorted: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime());

        assertArrayEquals(expected, arr);
    }

    @Test
    void testClassicShellSortLargeRandomInput() {
        MetricsCollector metrics = new MetricsCollector();
        int[] arr = new Random().ints(10_000, 0, 1_000_000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        ClassicShellSort.sort(arr, metrics);

        System.out.printf("ClassicShellSort large random: comps=%d, swaps=%d, accesses=%d, time=%.3f ms%n",
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime());

        assertArrayEquals(expected, arr);
        assertTrue(metrics.time().getElapsedTime() > 0, "No time recorded for large input");
    }

    @Test
    void testClassicShellSortEdgeCases() {
        MetricsCollector metrics = new MetricsCollector();

        // Empty array
        int[] empty = {};
        ClassicShellSort.sort(empty, metrics);
        assertArrayEquals(new int[]{}, empty);

        // Single element
        int[] single = {42};
        ClassicShellSort.sort(single, metrics);
        assertArrayEquals(new int[]{42}, single);

        System.out.printf("ClassicShellSort edge cases: time=%.3f ms%n", metrics.time().getElapsedTime());
    }
}
