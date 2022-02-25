package com.cythr.greenhouseapi.constants;

import static javax.management.timer.Timer.ONE_HOUR;

/**
 * Number of milliseconds for all dates
 */
@SuppressWarnings("PMD.UseUtilityClass")
public class EnumDates {
    /**
     * Number of milliseconds in one day
     */
    public static final long ONE_DAY = ONE_HOUR*24;
    /**
     * Number of milliseconds in five days
     */
    public static final long FIVE_DAYS = ONE_DAY*5;
    /**
     * Number of milliseconds in ten days
     */
    public static final long TEN_DAYS = FIVE_DAYS*2;
    /**
     * Number of milliseconds in one month
     */
    public static final long ONE_MONTH = TEN_DAYS*3;
    /**
     * Number of milliseconds in three months
     */
    public static final long THREE_MONTHS = ONE_MONTH*3;
    /**
     * Number of milliseconds in six months
     */
    public static final long SIX_MONTHS = ONE_MONTH*6;

    /**
     * @param date
     * @return Number of milliseconds for specific period (long)
     */
    @SuppressWarnings("PMD.OnlyOneReturn")
    public static long getDate(final String date){
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
