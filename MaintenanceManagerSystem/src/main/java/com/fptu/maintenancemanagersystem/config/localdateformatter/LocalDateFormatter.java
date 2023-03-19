package com.fptu.maintenancemanagersystem.config.localdateformatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {
    private final DateTimeFormatter formatter;

    public LocalDateFormatter(String pattern) {
this.formatter = DateTimeFormatter.ofPattern(pattern);
    }
    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return formatter.format(localDate);
    }
}
