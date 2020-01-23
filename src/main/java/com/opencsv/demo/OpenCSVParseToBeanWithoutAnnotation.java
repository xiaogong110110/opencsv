package com.opencsv.demo;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class OpenCSVParseToBeanWithoutAnnotation {
    private static final String SAMPLE_CSV_FILE_PATH = "./datadownload.csv";

    public static void main(String[] args) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        ) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Stock.class);
            String[] memberFieldsToBindTo = {"Data", "Open", "High", "Low", "Close", "Volume"};
            strategy.setColumnMapping(memberFieldsToBindTo);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            CsvToBean<Stock> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<Stock> stockIterator = csvToBean.iterator();

            while (stockIterator.hasNext()) {
                Stock stock= stockIterator.next();
                System.out.println("Date : " + stock.getDate().format(formatter));
                System.out.println("Open : " + stock.getOpen());
                System.out.println("High : " + stock.getHigh());
                System.out.println("Low : " + stock.getLow());
                System.out.println("Low : " + stock.getClose());
                System.out.println("Low : " + stock.getVolume());
                System.out.println("---------------------------");
            }
        }
    }

    // Reads all CSV contents into memory (Not suitable for large CSV files)
    private static void readAllBeansAtOnce(CsvToBean csvToBean) {
        List<Stock> stocks = csvToBean.parse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Stock stock : stocks) {
            System.out.println("Date : " + stock.getDate().format(formatter));
            System.out.println("Open : " + stock.getOpen());
            System.out.println("High : " + stock.getHigh());
            System.out.println("Low : " + stock.getLow());
            System.out.println("Low : " + stock.getClose());
            System.out.println("Low : " + stock.getVolume());
            System.out.println("---------------------------");
        }
    }
}
