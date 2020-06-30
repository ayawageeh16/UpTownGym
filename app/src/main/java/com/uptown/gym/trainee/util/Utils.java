package com.uptown.gym.trainee.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {

    /**
     * Method to convert long date to String
     *
     * @param date
     * @return
     */
    public static String convertLongDateToString(long date) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        Date startDate = new Date(date);
        return formatter.format(startDate);
    }

    public static long convertStringToDate(String date) {
        long longDate = 0;
        // Convert time and date to long
        SimpleDateFormat firstFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        try {
            Date startDate = firstFormatter.parse(date);
            longDate = startDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longDate;
    }

    public static long convertStringTimeToLong(int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, hour, min);
        return calendar.getTimeInMillis();
    }

    public static String convertLongTimeToString(long time) {
        GregorianCalendar cal = new GregorianCalendar();
        StringBuffer sBuf = new StringBuffer(8);

        cal.setTime(new Date(time));

        int hour = cal.get(Calendar.HOUR);

        if (hour == 0)
            hour = 12;

        if (hour < 10)
            sBuf.append(" ");

        sBuf.append(Integer.toString(hour));
        sBuf.append(":");

        int minute = cal.get(Calendar.MINUTE);

        if (minute < 10)
            sBuf.append("0");

        sBuf.append(Integer.toString(minute));
        sBuf.append(" ");
        sBuf.append(cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

        return (sBuf.toString());
    }
}
