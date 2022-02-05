package com.cythr.greenhouseapi.models.auxiliary;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class MoistureAux {
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float Moisture;

    public MoistureAux(Date date, Float moisture) {
        this.date = date;
        Moisture = moisture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getMoisture() {
        return Moisture;
    }

    public void setMoisture(Float moisture) {
        Moisture = moisture;
    }
}
