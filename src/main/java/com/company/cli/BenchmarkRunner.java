package com.company.cli;

import com.company.algorithms.ClassicShellSort;
import com.company.algorithms.ShellSort;
import com.company.metricsAndCsv.csvWriter.CsvWriter;
import com.company.metricsAndCsv.metrics.MetricsCollector;

import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        String outputPath = "docs/performance-plots/benchmark-results.csv";

        CsvWriter writer = new CsvWriter(outputPath);
        writer.writeHeader();

        MetricsCollector metrics = new MetricsCollector();

        for (int n : sizes) {
            System.out.println("\n===============================");
            System.out.println("Benchmarking array size: " + n);
            System.out.println("===============================");

            int[] baseData = generateRandomArray(n);

            // Classic Shell Sorts
            int[] classic = Arrays.copyOf(baseData, baseData.length);
            metrics.reset();
            ClassicShellSort.sort(classic, metrics);
            record(writer, "ClassicShellSort", n, metrics);

            // Optimized Shell Sorts
            for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
                int[] arrCopy = Arrays.copyOf(baseData, baseData.length);
                metrics.reset();
                ShellSort.sort(arrCopy, metrics, seq);
                record(writer, "ShellSort-" + seq.name(), n, metrics);
            }
        }

        System.out.println("\n All results written to: " + outputPath);
    }

    private static void record(CsvWriter writer, String algo, int n, MetricsCollector metrics) {
        String row = String.format(
                "%s,random,%d,%d,%d,%d,%d,%.3f",
                algo,
                n,
                1,
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime()
        );
        writer.appendRow(row);
        System.out.printf("%-20s | n=%-7d | time=%.3f ms | comps=%d | swaps=%d | accesses=%d%n",
                algo,
                n,
                metrics.time().getElapsedTime(),
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses());
    }

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100_000);
        }
        return arr;
    }
}