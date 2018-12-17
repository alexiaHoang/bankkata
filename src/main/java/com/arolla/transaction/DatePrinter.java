package com.arolla.transaction;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DatePrinter {
    private final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd");

    public String printDate() {
        LocalDate date = LocalDate.now();
        return date.toString(fmt);
    }
}
