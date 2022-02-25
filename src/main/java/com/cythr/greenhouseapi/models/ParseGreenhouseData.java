package com.cythr.greenhouseapi.models;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Class to rescue specific data columns from database
 */
public class ParseGreenhouseData {
    /**
     * date of data acquisition(TIMESTAMP)
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    /**
     * indoor data
     */
    private Float indoor;
    /**
     * outdoor data
     */
    private Float outdoor;

    /**
     * Constructor to rescue only date and indoor data
     * @param date Actual date
     * @param indoor Indoor data
     */
    public ParseGreenhouseData(final Date date, final Float indoor) {
        this.date = date;
        this.indoor = indoor;
    }

    /**
     * Constructor to rescue date, indoor and outdoor data
     * @param date Actual date
     * @param indoor Indoor data
     * @param outdoor Outdoor data
     */
    public ParseGreenhouseData(final Date date, final Float indoor, final Float outdoor) {
        this.date = date;
        this.indoor = indoor;
        this.outdoor = outdoor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Float getIndoor() {
        return indoor;
    }

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
