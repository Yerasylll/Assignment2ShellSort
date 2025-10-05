package com.company.algorithms;

import com.company.metricsAndCsv.metrics.MetricsCollector;
import java.util.Arrays;

public class ShellSort {

    public enum GapSequence {
        SHELL, KNUTH, SEDGEWICK
    }

    public static void sort(int[] arr, MetricsCollector metrics, GapSequence seq) {
        metrics.reset();
        metrics.time().start();

        int n = arr.length;
        if (n <= 1) {
            metrics.time().stop();
            return;
        }

        int[] gaps = generateGaps(n, seq);

        for (int g : gaps) {
            for (int i = g; i < n; i++) {
                int temp = arr[i];
                metrics.accesses().incAccesses(); // read arr[i]

                int j = i;
                while (j >= g) {
                    metrics.comparisons().incComparisons();
                    metrics.accesses().incAccesses(); // read arr[j-g]

                    if (arr[j - g] > temp) {
                        arr[j] = arr[j - g];
                        metrics.accesses().incAccesses(); // write
                        metrics.swaps().incSwaps();
                        j -= g;
                    } else {
                        break;
                    }
                }
                arr[j] = temp;
                metrics.accesses().incAccesses();
            }
        }

        metrics.time().stop();
    }

    // gap generators
    private static int[] generateGaps(int n, GapSequence seq) {
        switch (seq) {
            case SHELL: return shellGaps(n);
            case KNUTH: return knuthGaps(n);
            case SEDGEWICK: return sedgewickGaps(n);
            default: return new int[]{1};
        }
    }

    private static int[] shellGaps(int n) {
        if (n <= 1) return new int[]{1};
        int count = (int)(Math.log(n) / Math.log(2));
        int[] gaps = new int[count];
        int k = 0;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            gaps[k++] = gap;
        }
        return Arrays.copyOf(gaps, k);
    }

    private static int[] knuthGaps(int n) {
        if (n <= 1) return new int[]{1};
        int h = 1;
        int count = 0;
        while (h < n) {
            count++;
            h = 3 * h + 1;
        }
        int[] gaps = new int[count];
        h = 1;
        for (int i = 0; i < count; i++) {
            gaps[i] = h;
            h = 3 * h + 1;
        }
        reverse(gaps);
        return gaps;
    }

    private static int[] sedgewickGaps(int n) {
        if (n <= 1) return new int[]{1};
        int[] base = {1, 5, 19, 41, 109, 209, 505, 929,
                2161, 3905, 8929, 16001, 36289,
                64769, 146305, 260609, 587521,
                1045505, 2354689, 4188161};
        int count = 0;
        while (count < base.length && base[count] < n) count++;
        int[] gaps = Arrays.copyOf(base, count);
        reverse(gaps);
        return gaps;
    }

    private static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
