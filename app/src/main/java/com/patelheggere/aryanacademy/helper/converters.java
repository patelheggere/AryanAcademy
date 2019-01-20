package com.patelheggere.aryanacademy.helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class converters {
    public static String TimeStampToDate(long time)
    {
        Date date = new Date(time*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a "); // the format of your date
        return sdf.format(date);
    }
}
