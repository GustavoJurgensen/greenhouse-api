package com.cythr.greenhouseapi.models;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ParseGreenhouseData {
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float indoor;
    private Float outdoor;

    public ParseGreenhouseData(Date date, Float indoor) {
        this.date = date;
        this.indoor = indoor;
    }

    public ParseGreenhouseData(Date date, Float indoor, Float outdoor) {
        this.date = date;
        this.indoor = indoor;
        this.outdoor = outdoor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
