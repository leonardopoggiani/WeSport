package it.unipi.dsmt.utils;

import java.util.Calendar;

public class Utils {
    public static int getCalendarMonth(String month) {
        //System.out.println("Nella getCalendarMonth");
        switch (month) {
            case "JANUARY":
                return Calendar.JANUARY;
            case "FEBRUARY":
                return Calendar.FEBRUARY;
            case "MARCH":
                return Calendar.MARCH;
            case "APRIL":
                return Calendar.APRIL;
            case "MAY":
                return Calendar.MAY;
            case "JUNE":
                return Calendar.JUNE;
            case "JULY":
                return Calendar.JULY;
            case "AUGUST":
                return Calendar.AUGUST;
            case "SEPTEMBER":
                return Calendar.SEPTEMBER;
            case "OCTOBER":
                return Calendar.OCTOBER;
            case "NOVEMBER":
                return Calendar.NOVEMBER;
            default:
                return Calendar.DECEMBER;

        }
    }
}