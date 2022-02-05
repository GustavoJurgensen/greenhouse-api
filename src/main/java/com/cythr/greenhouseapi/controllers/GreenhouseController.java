package com.cythr.greenhouseapi.controllers;

import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.models.auxiliary.*;
import com.cythr.greenhouseapi.repositories.GreenhouseDataRepository;
import com.cythr.greenhouseapi.repositories.GreenhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.cythr.greenhouseapi.constants.EnumDates.getDate;

@RestController
@RequestMapping(value = "/greenhouse")
public class GreenhouseController {

    @Autowired
    private GreenhouseRepository greenhouseRepository;
    @Autowired
    private GreenhouseDataRepository greenhouseDataRepository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Greenhouse>> greenhouseList(){
        List<Greenhouse> list = (List<Greenhouse>)greenhouseRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/{addr}", produces = "application/json")
    public ResponseEntity<Greenhouse> findByAddr(@PathVariable (value = "addr") String addr){
        Greenhouse gh = greenhouseRepository.findByAddr(addr);
        return new ResponseEntity<>(gh, HttpStatus.OK);
    }
    @GetMapping(value = "/data/", produces = "application/json")
    public ResponseEntity<List<GreenhouseData>> greenhouseDataList(){
        List<GreenhouseData> list = (List<GreenhouseData>)greenhouseDataRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/data/humidity/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<HumidityAux>> greenhouseDataHumidityListByTime(@PathVariable (value = "addr") String addr,
                                                                              @PathVariable (value = "date") String date){
        Timestamp tm = new Timestamp(new Date().getTime()- getDate(date));
        List<HumidityAux> list = greenhouseDataRepository.findHumidityByDate(tm,addr);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/data/temperature/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<TemperatureAux>> greenhouseDataTemperatureListByTime(@PathVariable (value = "addr") String addr,
                                                                                 @PathVariable (value = "date") String date){
        Timestamp tm = new Timestamp(new Date().getTime()- getDate(date));
        List<TemperatureAux> list = greenhouseDataRepository.findTemperatureByDate(tm,addr);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/data/moisture/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<MoistureAux>> greenhouseDataMoistureListByTime(@PathVariable (value = "addr") String addr,
                                                                                    @PathVariable (value = "date") String date){
        Timestamp tm = new Timestamp(new Date().getTime()- getDate(date));
        List<MoistureAux> list = greenhouseDataRepository.findMoistureByDate(tm,addr);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/data/luminosity/addr/{addr}/date/{date}", produces = "application/json")
    public ResponseEntity<List<LuminosityAux>> greenhouseDataLuminosityListByTime(@PathVariable (value = "addr") String addr,
                                                                                    @PathVariable (value = "date") String date){
        Timestamp tm = new Timestamp(new Date().getTime()- getDate(date));
        List<LuminosityAux> list = greenhouseDataRepository.findLuminosityByDate(tm,addr);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Greenhouse> add(@RequestBody Greenhouse greenhouse){
        Greenhouse gh = greenhouseRepository.save(greenhouse);
        return new ResponseEntity<>(gh, HttpStatus.OK);
    }
    @PostMapping(value = "/data/", produces = "application/json")
    public ResponseEntity<GreenhouseData> add(@RequestBody GreenhouseData greenhouseData){
        Date date = new Date();
        Timestamp tm = new Timestamp(date.getTime());
        greenhouseData.setDate(tm);
        GreenhouseData gh = greenhouseDataRepository.save(greenhouseData);
        return new ResponseEntity<>(gh, HttpStatus.OK);
    }


    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Greenhouse> update(@RequestBody Greenhouse greenhouse){
        Greenhouse gh = greenhouseRepository.save(greenhouse);
        return new ResponseEntity<>(gh, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{addr}", produces = "application/json")
    public ResponseEntity<Greenhouse> deleteByAddr(@PathVariable (value = "addr") String addr){
        Greenhouse gh = greenhouseRepository.findByAddr(addr);
        greenhouseRepository.deleteByAddr(addr);
        greenhouseDataRepository.deleteByAddr(addr);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
