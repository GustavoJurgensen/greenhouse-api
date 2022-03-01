package com.cythr.greenhouseapi.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ParseGreenhouseDataString {
    private String date;
    private Float indoor;
    private Float outdoor;

    public ParseGreenhouseDataString(Date date, Float indoor, Float outdoor, String datebase) {
        TimeZone tz= TimeZone.getTimeZone("GMT-3:00");
        DateFormat dateFormat;
        if(datebase.equals("oneDay")){
            dateFormat = new SimpleDateFormat("HH:mm");
        }else{
            dateFormat = new SimpleDateFormat("dd/MM/yy");
        }
        dateFormat.setTimeZone(tz);
        String sDate = dateFormat.format(date);
        this.date = sDate;
        this.indoor = indoor;
        this.outdoor = outdoor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getIndoor() {
        return indoor;
    }

    public void setIndoor(Float indoor) {
        this.indoor = indoor;
    }

    public Float getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(Float outdoor) {
        this.outdoor = outdoor;
    }
}
