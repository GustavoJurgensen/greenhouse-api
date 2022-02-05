package com.cythr.greenhouseapi.constants;

import static javax.management.timer.Timer.ONE_HOUR;

public class EnumDates {
    public static final long ONE_DAY = ONE_HOUR*24;
    public static final long FIVE_DAYS = ONE_DAY*5;
    public static final long TEN_DAYS = FIVE_DAYS*2;
    public static final long ONE_MONTH = TEN_DAYS*3;
    public static final long THREE_MONTHS = ONE_MONTH*3;
    public static final long SIX_MONTHS = ONE_MONTH*6;

    public static long getDate(String date){
        switch (date){
            case "oneDay":
                return ONE_DAY;
            case "thirtyDays":
                return ONE_MONTH;
            case "sixMonths":
                return SIX_MONTHS;
            default:
                return FIVE_DAYS;
        }
    }
}
