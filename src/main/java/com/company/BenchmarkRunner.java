package com.company;

import com.company.algorithms.ClassicShellSort;
import com.company.algorithms.ShellSort;
import com.company.metricsAndCsv.metrics.MetricsCollector;
import com.company.metricsAndCsv.csvWriter.CsvWriter;

import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {

        // just setup
        int size = 10000;
        int[] data = generateRandomArray(size);
        CsvWriter writer = new CsvWriter("benchmark-results.csv");
        writer.writeHeader();

        MetricsCollector metrics = new MetricsCollector();

        // Classic Shell Sort
        int[] classic = Arrays.copyOf(data, data.length);
        ClassicShellSort.sort(classic, metrics);
        record(writer, "ClassicShellSort", size, metrics);

        // Optimized Shell Sort (SHELL)
        int[] shell = Arrays.copyOf(data, data.length);
        metrics.reset();
        ShellSort.sort(shell, metrics, ShellSort.GapSequence.valueOf("SHELL"));
        record(writer, "ShellSort-SHELL", size, metrics);

        // Optimized Shell Sort (KNUTH)
        int[] knuth = Arrays.copyOf(data, data.length);
        metrics.reset();
        ShellSort.sort(knuth, metrics, ShellSort.GapSequence.valueOf("KNUTH"));
        record(writer, "ShellSort-KNUTH", size, metrics);

        // Optimized Shell Sort (SEDGEWICK)
        int[] sedgewick = Arrays.copyOf(data, data.length);
        metrics.reset();
        ShellSort.sort(sedgewick, metrics, ShellSort.GapSequence.valueOf("SEDGEWICK"));
        record(writer, "ShellSort-SEDGEWICK", size, metrics);

        System.out.println("All results written to benchmark-results.csv");
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
                metrics.time().getElapsedTime() // convert ms → ns
        );
        writer.appendRow(row);
        System.out.printf("%s done: %.3f ms%n", algo, metrics.time().getElapsedTime());
    }

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(100000);
        return arr;
    }
}