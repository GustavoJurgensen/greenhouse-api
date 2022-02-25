package com.cythr.greenhouseapi.controllers;

import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.ParseGreenhouseData;
import com.cythr.greenhouseapi.models.ParseGreenhouseDataString;
import com.cythr.greenhouseapi.repositories.GreenhouseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cythr.greenhouseapi.constants.EnumDates.getDate;

/**
 * Controller for Greenhouse Data Requests HTTP
 */
@RestController
@RequestMapping("/greenhouse/data")
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class GreenhouseDataController {
    /**
     * Reference Greenhouse Data table
     */
    @Autowired
    private GreenhouseDataRepository greenhouseDataRepository;

    // -------------  Begin Data Getters ---------------
    /**
     * Get all data from greenhouse_data table
     * @return List<GreenhouseData>
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<GreenhouseData>> greenhouseDataList(){
        final List<GreenhouseData> list = (List<GreenhouseData>)greenhouseDataRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /**
     * Get date, inHumidity and outHumidity from greenhouse_data table
     * @return List<GreenhouseDataString>
     */
    @GetMapping(value = "/humidity/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<ParseGreenhouseDataString>> greenhouseDataHumidityListByTime(@PathVariable("addr") final String addr,
                                                                                            @PathVariable ("date") final String date){
        final Timestamp time = new Timestamp(new Date().getTime()- getDate(date));
        final List<ParseGreenhouseData> list = greenhouseDataRepository.findHumidityByDate(time,addr);
        return getListResponseEntity(date, list);
    }
    /**
     * Get date, inTemperature and outTemperature from greenhouse_data table
     * @return List<GreenhouseDataString>
     */
    @GetMapping(value = "/temperature/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<ParseGreenhouseDataString>> greenhouseDataTemperatureListByTime(@PathVariable ("addr") final String addr,
                                                                                               @PathVariable ("date") final String date){
        final Timestamp time = new Timestamp(new Date().getTime()- getDate(date));
        final List<ParseGreenhouseData> list = greenhouseDataRepository.findTemperatureByDate(time,addr);
        return getListResponseEntity(date, list);
    }
    /**
     * Get date and moisture from greenhouse_data table
     * @return List<GreenhouseDataString>
     */
    @GetMapping(value = "/moisture/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<ParseGreenhouseDataString>> greenhouseDataMoistureListByTime(@PathVariable ("addr") final String addr,
                                                                                            @PathVariable ("date") final String date){
        final Timestamp time = new Timestamp(new Date().getTime()- getDate(date));
        final List<ParseGreenhouseData> list = greenhouseDataRepository.findMoistureByDate(time,addr);
        return getListResponseEntity(date, list);
    }
    /**
     * Get date and luminosity from greenhouse_data table
     * @return List<GreenhouseDataString>
     */
    @GetMapping(value = "/luminosity/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<ParseGreenhouseDataString>> greenhouseDataLuminosityListByTime(@PathVariable ("addr") final String addr,
                                                                                              @PathVariable ("date") final String date){
        final Timestamp time = new Timestamp(new Date().getTime()- getDate(date));
        final List<ParseGreenhouseData> list = greenhouseDataRepository.findLuminosityByDate(time,addr);
        return getListResponseEntity(date, list);
    }

    private ResponseEntity<List<ParseGreenhouseDataString>> getListResponseEntity(@PathVariable("date") final String date, final List<ParseGreenhouseData> list) {
        List<ParseGreenhouseDataString> dataList = new ArrayList<>();
        for(ParseGreenhouseData greenhouse : list){
            dataList.add(new ParseGreenhouseDataString( greenhouse.getDate(),greenhouse.getIndoor(),greenhouse.getOutdoor(),date));
        }
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }
    // -------------  End Data Getters ---------------


    /**
     * Insert object on table greenhouse_data
     * @param greenhouseData GreenhouseData object
     * @return GreenhouseData
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<GreenhouseData> add(@RequestBody final GreenhouseData greenhouseData){
        final Date date = new Date();
        final Timestamp time = new Timestamp(date.getTime());
        greenhouseData.setDate(time);
        return new ResponseEntity<>(greenhouseDataRepository.save(greenhouseData), HttpStatus.OK);
    }
}
