package com.cythr.greenhouseapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Greenhouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cropType;
    private String addr;
    private Float tLuminosity;
    private Float tTemperature;
    private Float tHumidity;
    private Float tMoisture;

    public Greenhouse() {
    }

    public Greenhouse(Long id, String cropType, String addr, Float tLuminosity, Float tTemperature, Float tHumidity, Float tMoisture) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Float gettLuminosity() {
        return tLuminosity;
    }

    public void settLuminosity(Float tLuminosity) {
        this.tLuminosity = tLuminosity;
    }

    public Float gettTemperature() {
        return tTemperature;
    }

    public void settTemperature(Float tTemperature) {
        this.tTemperature = tTemperature;
    }

    public Float gettHumidity() {
        return tHumidity;
    }

    public void settHumidity(Float tHumidity) {
        this.tHumidity = tHumidity;
    }

    public Float gettMoisture() {
        return tMoisture;
    }

    public void settMoisture(Float tMoisture) {
        this.tMoisture = tMoisture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Greenhouse that = (Greenhouse) o;
        return Objects.equals(id, that.id) && Objects.equals(cropType, that.cropType) && Objects.equals(addr, that.addr) && Objects.equals(tLuminosity, that.tLuminosity) && Objects.equals(tTemperature, that.tTemperature) && Objects.equals(tHumidity, that.tHumidity) && Objects.equals(tMoisture, that.tMoisture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cropType, addr, tLuminosity, tTemperature, tHumidity, tMoisture);
    }
}
