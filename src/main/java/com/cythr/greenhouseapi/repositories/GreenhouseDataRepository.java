package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.auxiliary.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface GreenhouseDataRepository extends CrudRepository<GreenhouseData, Long> {

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.auxiliary.HumidityAux(gh.date, gh.Humidity, gh.exHumidity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<HumidityAux> findHumidityByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.auxiliary.TemperatureAux(gh.date, gh.Temperature, gh.exTemperature) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<TemperatureAux> findTemperatureByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.auxiliary.LuminosityAux(gh.date, gh.Luminosity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<LuminosityAux> findLuminosityByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.auxiliary.MoistureAux(gh.date, gh.Moisture) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<MoistureAux> findMoistureByDate(@Param("date")Date date, @Param("addr") String addr);

    @Transactional
    void deleteByAddr(@Param("addr") String addr);
}
