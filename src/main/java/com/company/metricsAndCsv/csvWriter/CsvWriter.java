package com.company.metricsAndCsv.csvWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private final String filePath;

    public CsvWriter(String filePath) {this.filePath = filePath;}

    public void writeHeader() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("algorithm,inputType,n,trial,comparisons,swaps,accesses,timeMS\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendRow(String row) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(row + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
