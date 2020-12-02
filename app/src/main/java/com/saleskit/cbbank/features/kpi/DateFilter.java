package com.saleskit.cbbank.features.kpi;

import java.io.Serializable;

public class DateFilter implements Serializable {
    private int year;
    private int quarter;
    private int month;

    public DateFilter(int year, int quarter, int month) {
        this.year = year;
        this.quarter = quarter;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "DateFilter{" +
                "year=" + year +
                ", quarter=" + quarter +
                ", month=" + month +
                '}';
    }
}
