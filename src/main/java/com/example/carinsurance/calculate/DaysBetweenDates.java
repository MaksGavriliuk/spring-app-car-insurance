package com.example.carinsurance.calculate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

public class DaysBetweenDates {

    public static long daysBetweenDates(String date1, String date2) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return ChronoUnit.DAYS.between(
                    dateFormat.parse(date1).toInstant(),
                    dateFormat.parse(date2).toInstant()
            );
        } catch (ParseException e) {
            throw new RuntimeException("Ошибка при преобразовании даты: " + e.getMessage(), e);
        }
    }

}
