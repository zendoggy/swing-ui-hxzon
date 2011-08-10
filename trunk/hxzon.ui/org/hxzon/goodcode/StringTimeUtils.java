package org.hxzon.goodcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTimeUtils {
    private static SimpleDateFormat format = new SimpleDateFormat();
    private static String pattern;
    private static Date date;

    public static Date parse(String timeStr) {
        return parse(timeStr, pattern);
    }

    public static Date parse(String timeStr, String pattern) {
        Date date = null;
        format.applyPattern(pattern);
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String format(Date date) {
        return format(date, pattern);
    }

    public static String format(Date date, String pattern) {
        format.applyPattern(pattern);
        return format.format(date);
    }
    
    public static String format(String timeStr,String formPattern){
        return convertPattern(timeStr,formPattern,pattern);
    }

    public static String convertPattern(String timeStr, String formPattern, String toPattern) {
        Date date = null;
        format.applyPattern(formPattern);
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
            return null;
        }
        format.applyPattern(toPattern);
        return format.format(date);

    }
    
    public static int compare(String timeStr1,String timeStr2){
        return compare(timeStr1,timeStr2,pattern);
    }

    public static int compare(String timeStr1, String timeStr2, String pattern) {
        Date date1 = null;
        Date date2 = null;
        format.applyPattern(pattern);
        try {
            date1 = format.parse(timeStr1);
            date2 = format.parse(timeStr2);
        } catch (ParseException e) {
            return -2;
        }
        return date1.compareTo(date2);
    }

    public void setPattern(String pattern) {
        if (pattern != null) {
            this.pattern = pattern;
        }
    }

    public String getPattern() {
        return pattern;
    }

    public static void main(String[] args) {

    }

}
