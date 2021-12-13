package com.digitaloasis.openweather.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {
    public static String DATE_FORMAT = "MMMM yy";
    public static String DAY_WEEK_TEXT = "EEEE";
    public static String DAY = "dd";

    public static String getDateFormatStringFromTimeMillis(long milliSeconds){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds*1000L);
        return "Today, " + formatter.format(calendar.getTime());
    }

    public static String getDateStringFromTimeMillis(long milliSeconds){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds*1000L);
        return formatter.format(calendar.getTime());
    }

    public static String getDayWeekTextFromTimeMillis(long milliSeconds){
        SimpleDateFormat formatter = new SimpleDateFormat(DAY_WEEK_TEXT);
        SimpleDateFormat formatterDay = new SimpleDateFormat(DAY);

        Calendar calendarDay= Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds*1000L);

        String dayCalendar = formatterDay.format(calendarDay.getTime());
        String calendarMillis = formatterDay.format(calendar.getTime());
        if (Integer.parseInt(dayCalendar) == Integer.parseInt(calendarMillis)){
            return "Today";
        } else if (Integer.parseInt(dayCalendar) + 1 == Integer.parseInt(calendarMillis)){
            return "Tomorrow";
        } else {
            return formatter.format(calendar.getTime());
        }
    }
}
