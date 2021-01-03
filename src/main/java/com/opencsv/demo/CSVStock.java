package com.opencsv.demo;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CSVStock {

    @CsvBindByName(column = "Date", required = true)
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @CsvBindByName(column = "Open")
    private double open;

    @CsvBindByName(column = "High")
    private double high;

    @CsvBindByName(column = "Low")
    private double low;

    @CsvBindByName(column = "Close")
    private double close;

    @CsvBindByName(column = "Volume")
    private double volume;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
