package com.example.refactortask1.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static java.time.ZonedDateTime.now;

public class TimeUtils {

    public static Long getStartTimeOfDayBeforeXDay(Long time, Integer numDay) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -numDay);
        return Long.parseLong(format.format(date));
//        return getZonedDateTime(time).minusDays(numDay);
    }

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

    public static String convertToString(Long time) {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return formatter.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getMonth(1616173200000L));
        System.out.println(getDayOfMonth(1616173200000L));
        System.out.println(convertToString(1616173200000L));
        System.out.println(convertToString(getStartTimeOfDayFromTime(1616173200000L)));
        System.out.println(convertToString(getEndTime(1616173200000L)));
    }

}
