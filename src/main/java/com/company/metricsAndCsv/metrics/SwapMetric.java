package com.company.metricsAndCsv.metrics;

public class SwapMetric {
    private long swaps = 0;

    public void incSwaps() { swaps++; }
    public void incSwaps(long delta) { swaps += delta; }
    public long getSwaps() { return swaps; }
    public void reset() { swaps = 0; }
}
