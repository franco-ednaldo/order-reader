package com.example.orderreader.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {
    public static LocalDate formatStringToLocalDate(final String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        return LocalDate.parse(date, formatter);

    }
}