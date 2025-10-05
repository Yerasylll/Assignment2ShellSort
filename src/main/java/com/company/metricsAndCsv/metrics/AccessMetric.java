package com.company.metricsAndCsv.metrics;

public class AccessMetric {
    private long accesses = 0;

    public void incAccesses() { accesses++; }
    public void incAccesses(long delta) { accesses += delta; }
    public long getAccesses() { return accesses; }
    public void reset() { accesses = 0; }
}
