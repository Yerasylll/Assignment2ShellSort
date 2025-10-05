package com.company.algorithms;

import com.company.metricsAndCsv.metrics.MetricsCollector;

public class ClassicShellSort {
    public static void sort(int[] arr, MetricsCollector metrics) {

        metrics.reset();
        metrics.time().start();

        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                metrics.accesses().incAccesses();

                int j = i;
                while (j >= gap) {
                    metrics.comparisons().incComparisons();
                    metrics.accesses().incAccesses();

                    if (arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        metrics.accesses().incAccesses();
                        metrics.swaps().incSwaps();
                        j -= gap;
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
}
