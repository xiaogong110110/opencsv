package com.opencsv.demo;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class OpenCSVReadAndParseToBean {
    private static final String SAMPLE_CSV_FILE_PATH = "./datadownload.csv";

    public static void main(String[] args) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            CsvToBean<CSVStock> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStock.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStock> csvStockIterator = csvToBean.iterator();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



            while (csvStockIterator.hasNext()) {
                CSVStock csvStock = csvStockIterator.next();
                System.out.println("Date : " + csvStock.getDate().format(formatter));
                System.out.println("Open : " +csvStock.getOpen());
                System.out.println("High : " +csvStock.getHigh());
                System.out.println("Low : " + csvStock.getLow());
                System.out.println("Low : " + csvStock.getClose());
                System.out.println("Low : " + csvStock.getVolume());
                System.out.println("==========================");
            }
        }
    }

    // Reads all CSV contents into memory (Not suitable for large CSV files)
    private static void readAllBeansAtOnce(CsvToBean csvToBean) {
        List<CSVStock> csvStocks = csvToBean.parse();

        for(CSVStock csvStock: csvStocks) {
            System.out.println("Date : " + csvStock.getDate().toString());
            System.out.println("Open : " + csvStock.getOpen());
            System.out.println("High : " + csvStock.getHigh());
            System.out.println("Low : " + csvStock.getLow());
            System.out.println("Low : " + csvStock.getClose());
            System.out.println("Low : " + csvStock.getVolume());
            System.out.println("==========================");
        }
    }
}
