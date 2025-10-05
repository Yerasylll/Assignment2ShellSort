package com.company.metricsAndCsv.metrics;


public class TimeMetric {
    private long startTime;
    private long elapsedTime;

    public void start() { startTime = System.nanoTime(); }

    public void stop() { elapsedTime = System.nanoTime() - startTime; }

    public void reset() { elapsedTime = 0; }

    public double getElapsedTime() { return elapsedTime / 1_000_000.0; } // ms

}
