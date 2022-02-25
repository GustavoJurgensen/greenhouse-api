package com.cythr.greenhouseapi.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Entity to Greenhouse data acquisitions
 */
@Entity
@Builder
public class GreenhouseData {
    /**
     * Data ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Greenhouse Address
     */
    private String addr;
    /**
     * Date of data acquisition
     */
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    /**
     * Luminosity sensor data
     */
    private Float luminosity;
    /**
     * Indoor temperature sensor data
     */
    private Float temperature;
    /**
     * Indoor humidity sensor data
     */
    private Float humidity;
    /**
     * Moisture sensor data
     */
    private Float moisture;
    /**
     * Outdoor temperature sensor data
     */
    private Float exTemperature;
    /**
     * Outdoor humidity sensor data
     */
    private Float exHumidity;
    /**
     * Wind sensor data
     */
    private Float exWind;

    /**
     * Creates instance of {@link GreenhouseData}.
     */
    public GreenhouseData() {
        //Empty constructor because lombok need to make a builder
    }

    /**
     * Greenhouse Data Constructor
     * @param id Data ID
     * @param addr Greenhouse address
     * @param date Date of data acquisition
     * @param luminosity Luminosity sensor data
     * @param temperature Indoor temperature sensor data
     * @param humidity Indoor humidity sensor data
     * @param moisture Moisture sensor data
     * @param exTemperature Outdoor temperature sensor data
     * @param exHumidity Outdoor humidity sensor data
     * @param exWind Wind sensor data
     */
    public GreenhouseData(final Long id, final String addr, final Date date, final Float luminosity, final Float temperature, final Float humidity, final Float moisture, final Float exTemperature, final Float exHumidity, final Float exWind) {
        this.id = id;
        this.addr = addr;
        this.date = date;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.humidity = humidity;
        this.moisture = moisture;
        this.exTemperature = exTemperature;
        this.exHumidity = exHumidity;
        this.exWind = exWind;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(final String addr) {
        this.addr = addr;
    }

    public Float getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(final Float luminosity) {
        this.luminosity = luminosity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(final Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(final Float humidity) {
        this.humidity = humidity;
    }

    public Float getMoisture() {
        return moisture;
    }

    public void setMoisture(final Float moisture) {
        this.moisture = moisture;
    }

    public Float getExTemperature() {
        return exTemperature;
    }

    public void setExTemperature(final Float exTemperature) {
        this.exTemperature = exTemperature;
    }

    public Float getExHumidity() {
        return exHumidity;
    }

    public void setExHumidity(final Float exHumidity) {
        this.exHumidity = exHumidity;
    }

    public Float getExWind() {
        return exWind;
    }

    public void setExWind(final Float exWind) {
        this.exWind = exWind;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean condition;
        if (this == obj) { condition = true; }
        else if (obj == null || getClass() != obj.getClass()) { condition = false; }
        else{
            final GreenhouseData that = (GreenhouseData) obj;
            condition = Objects.equals(id, that.id) && Objects.equals(addr, that.addr) && Objects.equals(luminosity, that.luminosity) && Objects.equals(temperature, that.temperature) && Objects.equals(humidity, that.humidity) && Objects.equals(moisture, that.moisture) && Objects.equals(exTemperature, that.exTemperature) && Objects.equals(exHumidity, that.exHumidity) && Objects.equals(exWind, that.exWind);
        }
        return condition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addr, date, luminosity, temperature, humidity, moisture, exTemperature, exHumidity, exWind);
    }
}
