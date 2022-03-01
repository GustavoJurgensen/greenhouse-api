package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.ParseGreenhouseData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * CrudRepository to Greenhouse Data Table
 */
@Repository
public interface GreenhouseDataRepository extends CrudRepository<GreenhouseData, Long> {

    /**
     * @param date date of begin period
     * @param addr greenhouse address
     * @return a List of ParseGreenhouseData with all humidity data since @date
     */
    @Query("SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.humidity, gh.exHumidity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findHumidityByDate(@Param("date")Date date, @Param("addr") String addr);

    /**
     * @param date date of begin period
     * @param addr greenhouse address
     * @return a List of ParseGreenhouseData with all temperature data since @date
     */
    @Query("SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.temperature, gh.exTemperature) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findTemperatureByDate(@Param("date")Date date, @Param("addr") String addr);

    /**
     * @param date date of begin period
     * @param addr greenhouse address
     * @return a List of ParseGreenhouseData with all luminosity data since @date
     */
    @Query("SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.luminosity) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findLuminosityByDate(@Param("date")Date date, @Param("addr") String addr);

    /**
     * @param date date of begin period
     * @param addr greenhouse address
     * @return a List of ParseGreenhouseData with all moisture data since @date
     */
    @Query("SELECT new com.cythr.greenhouseapi.models.ParseGreenhouseData(gh.date, gh.moisture) FROM GreenhouseData gh WHERE gh.date >= ?1 AND gh.addr = ?2")
    List<ParseGreenhouseData> findMoistureByDate(@Param("date")Date date, @Param("addr") String addr);

    /**
     * Delete by greenhouse address
     * @param addr greenhouse address
     */
    @Transactional
    void deleteByAddr( String addr);

    @Query(nativeQuery = true, value ="SELECT gh FROM GreenhouseData gh WHERE gh.addr = ?1 ORDER BY gh.date DESC LIMIT 1")
    GreenhouseData findLastByAddr(@Param("addr") String addr);
}
