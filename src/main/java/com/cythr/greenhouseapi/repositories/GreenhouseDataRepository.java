package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.ParseGreenhouseData;
import com.cythr.greenhouseapi.models.auxiliary.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface GreenhouseDataRepository extends CrudRepository<GreenhouseData, Long> {

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.Humidity, gh.exHumidity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findHumidityByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.Temperature, gh.exTemperature) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findTemperatureByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.Luminosity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findLuminosityByDate(@Param("date")Date date, @Param("addr") String addr);

    @Query(value = "SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.Moisture) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findMoistureByDate(@Param("date")Date date, @Param("addr") String addr);

    @Transactional
    void deleteByAddr(@Param("addr") String addr);
}
