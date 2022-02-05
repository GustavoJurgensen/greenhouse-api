package com.cythr.greenhouseapi.models.auxiliary;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class LuminosityAux {
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float Luminosity;

    public LuminosityAux(Date date, Float luminosity) {
        this.date = date;
        this.Luminosity = luminosity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getLuminosity() {
        return Luminosity;
    }

    public void setLuminosity(Float luminosity) {
        Luminosity = luminosity;
    }
}
