package com.company.metricsAndCsv.metrics;

public class ComparisonMetric {
    private long comparisons = 0;

    public void incComparisons() { comparisons++; }
    public void incComparisons(long delta) { comparisons += delta; }
    public long getComparisons() { return comparisons; }
    public void reset() { comparisons = 0; }
}