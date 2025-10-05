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
        String[] inputTypes = {"random", "sorted", "reversed", "nearly_sorted"};
        String outputPath = "docs/performance-plots/benchmark-results.csv";

        CsvWriter writer = new CsvWriter(outputPath);
        writer.writeHeader();

        MetricsCollector metrics = new MetricsCollector();

        for (String inputType : inputTypes) {
            System.out.println("\n===============================");
            System.out.println("Testing input type: " + inputType);
            System.out.println("===============================");

            for (int n : sizes) {
                int[] baseData = generateArray(n, inputType);

                System.out.printf("\n--- Array size: %d ---\n", n);

                // Classic Shell Sort
                int[] classic = Arrays.copyOf(baseData, baseData.length);
                metrics.reset();
                ClassicShellSort.sort(classic, metrics);
                record(writer, "ClassicShellSort", inputType, n, metrics);

                // Optimized Shell Sorts (SHELL, KNUTH, SEDGEWICK)
                for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
                    int[] arrCopy = Arrays.copyOf(baseData, baseData.length);
                    metrics.reset();
                    ShellSort.sort(arrCopy, metrics, seq);
                    record(writer, "ShellSort-" + seq.name(), inputType, n, metrics);
                }
            }
        }

        System.out.println("\n All results written to: " + outputPath);
    }

    private static void record(CsvWriter writer, String algo, String inputType, int n, MetricsCollector metrics) {
        String row = String.format(
                "%s,%s,%d,%d,%d,%d,%d,%.3f",
                algo,
                inputType,
                n,
                1,
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses(),
                metrics.time().getElapsedTime()
        );
        writer.appendRow(row);
        System.out.printf("%-20s | type=%-13s | n=%-7d | time=%.3f ms | comps=%d | swaps=%d | accesses=%d%n",
                algo,
                inputType,
                n,
                metrics.time().getElapsedTime(),
                metrics.comparisons().getComparisons(),
                metrics.swaps().getSwaps(),
                metrics.accesses().getAccesses());
    }

    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        Random rand = new Random();

        switch (type) {
            case "sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) arr[i] = n - i;
                break;
            case "nearly_sorted":
                for (int i = 0; i < n; i++) arr[i] = i;
                // introduce a few random swaps (5% of the array)
                for (int i = 0; i < Math.max(1, n / 20); i++) {
                    int a = rand.nextInt(n);
                    int b = rand.nextInt(n);
                    int temp = arr[a];
                    arr[a] = arr[b];
                    arr[b] = temp;
                }
                break;
            case "random":
            default:
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10);
                break;
        }

        return arr;
    }
}