package com.cythr.greenhouseapi.models.auxiliary;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class HumidityAux{

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float Humidity;

    public HumidityAux(Date date, Float humidity, Float exHumidity) {
        this.date = date;
        Humidity = humidity;
        this.exHumidity = exHumidity;
    }

    private Float exHumidity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getHumidity() {
        return Humidity;
    }

    public void setHumidity(Float humidity) {
        Humidity = humidity;
    }

    public Float getExHumidity() {
        return exHumidity;
    }

    public void setExHumidity(Float exHumidity) {
        this.exHumidity = exHumidity;
    }
}
