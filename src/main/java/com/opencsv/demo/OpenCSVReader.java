package com.opencsv.demo;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenCSVReader {
    private static final String SAMPLE_CSV_FILE_PATH = "datadownload.csv";

    public static void main(String[] args) throws IOException, CsvValidationException, CsvException {
        readRecordsOneByOne();
        readAllRecordsAtOnce();
    }

    private static void readRecordsOneByOne() throws IOException, CsvValidationException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println("Date   : " + nextRecord[0]);
                System.out.println("Open   : " + nextRecord[1]);
                System.out.println("High   : " + nextRecord[2]);
                System.out.println("Low    : " + nextRecord[3]);
                System.out.println("Close  : " + nextRecord[4]);
                System.out.println("Volume : " + nextRecord[5]);
                System.out.println("==========================");
            }
        }
    }

    private static void readAllRecordsAtOnce() throws IOException, CsvException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading All Records at once into a List<String[]>
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                System.out.println("Date   : " + record[0]);
                System.out.println("Open   : " + record[1]);
                System.out.println("High   : " + record[2]);
                System.out.println("Low    : " + record[3]);
                System.out.println("Close  : " + record[4]);
                System.out.println("Volume : " + record[5]);
                System.out.println("---------------------------");
            }
        }
    }
}
