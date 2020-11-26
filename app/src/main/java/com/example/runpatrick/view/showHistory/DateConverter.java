package com.example.runpatrick.view.showHistory;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {
    public static String convertToString(Date startDate) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europa/Paris"));
        cal.setTime(startDate);
        return cal.get(Calendar.DAY_OF_MONTH) + "." + ((cal.get(Calendar.MONTH) + 1)) + "." + cal.get(Calendar.YEAR);
    }
}
