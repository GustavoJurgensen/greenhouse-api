package com.cythr.greenhouseapi.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Class to format date, convert to string and add value to indoor and outdoor parameters
 */
public class ParseGreenhouseDataString {
    /**
     * date of data acquisition formatted by HH:mm:ss or dd/MM/yy
     */
    private String date;
    /**
     * indoor data
     */
    private Float indoor;
    /**
     * outdoor data
     */
    private Float outdoor;

    /**
     * Constructor
     * @param date Actual date
     * @param indoor Indoor data
     * @param outdoor Outdoor data
     * @param datebase flag to format date, oneday format in HH:mm:ss and others format in dd/MM/yy
     */
    public ParseGreenhouseDataString(final Date date, final Float indoor, final Float outdoor, final String datebase) {
        final TimeZone timeZone= TimeZone.getTimeZone("GMT-3:00");
        DateFormat dateFormat;
        if("oneDay".equals(datebase)){
            dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        }else{
            dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        }
        dateFormat.setTimeZone(timeZone);
        this.date = dateFormat.format(date);
        this.indoor = indoor;
        this.outdoor = outdoor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public Float getIndoor() { return indoor; }

    public void setIndoor(final Float indoor) {
        this.indoor = indoor;
    }

    public Float getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(final Float outdoor) {
        this.outdoor = outdoor;
    }
}
