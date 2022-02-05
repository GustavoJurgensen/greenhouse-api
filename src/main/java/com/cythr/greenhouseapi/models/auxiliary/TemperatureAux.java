package com.cythr.greenhouseapi.models.auxiliary;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class TemperatureAux {
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float Temperature;
    private Float exTemperature;

    public TemperatureAux(Date date, Float temperature, Float exTemperature) {
        this.date = date;
        this.Temperature = temperature;
        this.exTemperature = exTemperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTemperature() {
        return Temperature;
    }

    public void setTemperature(Float temperature) {
        Temperature = temperature;
    }

    public Float getExTemperature() {
        return exTemperature;
    }

    public void setExTemperature(Float exTemperature) {
        this.exTemperature = exTemperature;
    }
}
