package com.cythr.greenhouseapi.models;

import lombok.Builder;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Builder
public class GreenhouseData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String addr;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float Luminosity;
    private Float Temperature;
    private Float Humidity;
    private Float Moisture;
    private Float exTemperature;
    private Float exHumidity;
    private Float exWind;

    public GreenhouseData() {}

    public GreenhouseData(Long id, String addr, Date date, Float luminosity, Float temperature, Float humidity, Float moisture, Float exTemperature, Float exHumidity, Float exWind) {
        this.id = id;
        this.addr = addr;
        this.date = date;
        this.Luminosity = luminosity;
        this.Temperature = temperature;
        this.Humidity = humidity;
        this.Moisture = moisture;
        this.exTemperature = exTemperature;
        this.exHumidity = exHumidity;
        this.exWind = exWind;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Float getLuminosity() {
        return Luminosity;
    }

    public void setLuminosity(Float luminosity) {
        Luminosity = luminosity;
    }

    public Float getTemperature() {
        return Temperature;
    }

    public void setTemperature(Float temperature) {
        Temperature = temperature;
    }

    public Float getHumidity() {
        return Humidity;
    }

    public void setHumidity(Float humidity) {
        Humidity = humidity;
    }

    public Float getMoisture() {
        return Moisture;
    }

    public void setMoisture(Float moisture) {
        Moisture = moisture;
    }

    public Float getExTemperature() {
        return exTemperature;
    }

    public void setExTemperature(Float exTemperature) {
        this.exTemperature = exTemperature;
    }

    public Float getExHumidity() {
        return exHumidity;
    }

    public void setExHumidity(Float exHumidity) {
        this.exHumidity = exHumidity;
    }

    public Float getExWind() {
        return exWind;
    }

    public void setExWind(Float exWind) {
        this.exWind = exWind;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreenhouseData that = (GreenhouseData) o;
        return id.equals(that.id) && Objects.equals(addr, that.addr) && Objects.equals(date, that.date) && Objects.equals(Luminosity, that.Luminosity) && Objects.equals(Temperature, that.Temperature) && Objects.equals(Humidity, that.Humidity) && Objects.equals(Moisture, that.Moisture) && Objects.equals(exTemperature, that.exTemperature) && Objects.equals(exHumidity, that.exHumidity) && Objects.equals(exWind, that.exWind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addr, date, Luminosity, Temperature, Humidity, Moisture, exTemperature, exHumidity, exWind);
    }
}
