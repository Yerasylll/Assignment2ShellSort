# Design-AnalysisAssignment2

---

##  Project Overview

### Implemented Algorithms
- **ClassicShellSort** – The original Shell sort algorithm using the standard `n/2, n/4, …, 1` gap sequence.
- **ShellSort (Optimized)** – Enhanced versions with configurable gap sequences:
    - `SHELL` (default)
    - `KNUTH`
    - `SEDGEWICK`

Each algorithm is instrumented with a **MetricsCollector** that records:
- Comparisons
- Swaps
- Array accesses
- Execution time (in milliseconds)

---

## Components

| Package                               | Description |
|---------------------------------------|--------------|
| `com.company.algorithms`              | Contains sorting algorithm implementations (`ClassicShellSort`, `ShellSort`). |
| `com.company.metricsAndCsv.metrics`   | Defines metric tracking classes (`TimeMetric`, `ComparisonMetric`, `AccessMetric`, `SwapMetric`, `MetricsCollector`). |
| `com.company.metricsAndCsv.csvWriter` | Handles writing benchmark data to CSV files. |
| `com.company.cli`                     | Contains `BenchmarkRunner`, the main entry point for automated performance runs. |

---

## BenchmarkRunner

The **BenchmarkRunner** is a minimal command-line utility that runs all algorithms on randomly generated data and writes results to a CSV file.

### Example Run
```bash
# Run via IntelliJ or terminal
java src/main/java/com/company/cli/BenchmarkRunner.java
