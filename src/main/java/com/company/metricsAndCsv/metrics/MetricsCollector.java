package com.company.metricsAndCsv.metrics;

public class MetricsCollector {
    private final TimeMetric time = new TimeMetric();
    private final ComparisonMetric comparisons = new ComparisonMetric();
    private final AccessMetric accesses = new AccessMetric();
    private final SwapMetric swaps = new SwapMetric();

    public void reset() {
        time.reset();
        comparisons.reset();
        accesses.reset();
        swaps.reset();
    }

    public TimeMetric time() { return time; }
    public ComparisonMetric comparisons() { return comparisons; }
    public AccessMetric accesses() { return accesses; }
    public SwapMetric swaps() { return swaps; }
}
