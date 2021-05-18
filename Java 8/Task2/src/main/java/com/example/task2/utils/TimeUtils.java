package com.example.task2.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import static java.time.ZonedDateTime.now;

public class TimeUtils {

    public static Long getEndTime(Long time) {
        return getEndZonedDateTime(time)
                .toInstant().toEpochMilli();
    }

    public static Long getStartTimeOfDayFromTime(Long time) {
        return getZonedDateTime(time)
                .toInstant().toEpochMilli();
    }

    private static ZonedDateTime getZonedDateTime(Long time) {
        return now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withMonth(getMonth(time))
                .withDayOfMonth(getDayOfMonth(time))
                .withYear(getYear(time));
    }

    private static ZonedDateTime getEndZonedDateTime (Long time) {
        return now()
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withMonth(getMonth(time))
                .withDayOfMonth(getDayOfMonth(time))
                .withYear(getYear(time));
    }

    public static Long getDayFromTime (Long time) {
        return getDayDateTime(time)
                .toInstant().toEpochMilli();
    }

    public static Long getHourFromTime (Long time) {
        return getHourDateTime(time)
                .toInstant().toEpochMilli();
    }

    private static ZonedDateTime getDayDateTime (Long time) {
        return now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withDayOfMonth(getDayOfMonth(time))
                .withMonth(getMonth(time))
                .withYear(getYear(time));
    }

    private static ZonedDateTime getHourDateTime (Long time) {
        return now()
                .withHour(getHour(time))
                .withMinute(0)
                .withSecond(0)
                .withDayOfMonth(getDayOfMonth(time))
                .withMonth(getMonth(time))
                .withYear(getYear(time));
    }

    public static Long getOneDayMilli() {
        return 24 * 60 * 60 * 1000L;
    }

    public static int getDayOfMonth(Long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd");
        return Integer.parseInt(format.format(date));
    }

    public static int getMonth(Long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("MM");
        return Integer.parseInt(format.format(date));
    }

    public static int getYear(Long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy");
        return Integer.parseInt(format.format(date));
    }

    public static int getHour (Long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("hh");
        return Integer.parseInt(format.format(date));
    }

    public static String convertToString(Long time) {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return formatter.format(date);
    }
}
