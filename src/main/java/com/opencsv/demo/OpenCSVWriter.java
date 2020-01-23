package com.opencsv.demo;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OpenCSVWriter {
    private static final String STRING_ARRAY_SAMPLE = "./string-array-sample.csv";
    private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";

    public static void main(String[] args) throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {

        writeFromArrayOfStrings();
        writeFromListOfObjects();
    }


    private static void writeFromArrayOfStrings() throws IOException {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"Symbol", "Date", "Open", "High", "Low", "Close", "Volume"};
            csvWriter.writeNext(headerRecord);

            csvWriter.writeNext(new String[]{"AAPL", "20 Jan 2020", "318.67", "319.99", "317.31", "317.62", "24939532"});
            csvWriter.writeNext(new String[]{"AAPL", "21 Jan 2020", "317.19", "319.02", "316.00", "316.55", "26568271"});
        }
    }

    private static void writeFromListOfObjects() throws IOException,
            CsvDataTypeMismatchException,
            CsvRequiredFieldEmptyException {

        try (
                Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE));
        ) {
            StatefulBeanToCsv<Stock> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            List<Stock> myUsers = new ArrayList<>();
            myUsers.add(new Stock("AAPL", LocalDate.of(2020, 01, 20), 318.67, 319.99, 317.31, 317.62, 24939532));
            myUsers.add(new Stock("AAPL", LocalDate.of(2020, 01, 20),  317.19, 319.02, 316.00, 316.55, 26568271));

            beanToCsv.write(myUsers);
        }
    }
}
