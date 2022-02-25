package com.cythr.greenhouseapi.models;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity to Greenhouse parameters
 */
@Entity
@Builder
public class Greenhouse implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Greenhouse ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Type of crop
     */
    private String cropType;
    /**
     * Greenhouse Address
     */
    private String addr;
    /**
     * Target Luminosity
     */
    private Float tLuminosity;
    /**
     * Target Temperature
     */
    private Float tTemperature;
    /**
     * Target Humidity
     */
    private Float tHumidity;
    /**
     * Target Moisture
     */
    private Float tMoisture;

    /**
     * Creates instance of {@link Greenhouse}.
     */
    public Greenhouse() {
        //Empty constructor because lombok need to make a builder
    }

    /**
     * Greenhouse Constructor
     * @param id Greenhouse ID
     * @param cropType Type of crop
     * @param addr Greenhouse Address
     * @param tLuminosity Target Luminosity
     * @param tTemperature Target Temperature
     * @param tHumidity Target Humidity
     * @param tMoisture Target Moisture
     */
    public Greenhouse(final Long id, final String cropType, final String addr, final Float tLuminosity, final Float tTemperature, final Float tHumidity, final Float tMoisture) {
        this.id = id;
        this.cropType = cropType;
        this.addr = addr;
        this.tLuminosity = tLuminosity;
        this.tTemperature = tTemperature;
        this.tHumidity = tHumidity;
        this.tMoisture = tMoisture;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(final String cropType) {
        this.cropType = cropType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(final String addr) {
        this.addr = addr;
    }

    public Float gettLuminosity() {
        return tLuminosity;
    }

    public void settLuminosity(final Float tLuminosity) {
        this.tLuminosity = tLuminosity;
    }

    public Float gettTemperature() {
        return tTemperature;
    }

    public void settTemperature(final Float tTemperature) {
        this.tTemperature = tTemperature;
    }

    public Float gettHumidity() {
        return tHumidity;
    }

    public void settHumidity(final Float tHumidity) {
        this.tHumidity = tHumidity;
    }

    public Float gettMoisture() {
        return tMoisture;
    }

    public void settMoisture(final Float tMoisture) {
        this.tMoisture = tMoisture;
    }

    @Override
    public boolean equals(final Object object) {
        boolean cond;
        if (this == object) { cond = true; }
        else if (object == null || getClass() != object.getClass()) { cond = false; }
        else {
            final Greenhouse that = (Greenhouse) object;
            cond= Objects.equals(id, that.id) && Objects.equals(cropType, that.cropType) && Objects.equals(addr, that.addr) && Objects.equals(tLuminosity, that.tLuminosity) && Objects.equals(tTemperature, that.tTemperature) && Objects.equals(tHumidity, that.tHumidity) && Objects.equals(tMoisture, that.tMoisture);
        }
        return cond;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cropType, addr, tLuminosity, tTemperature, tHumidity, tMoisture);
    }
}
