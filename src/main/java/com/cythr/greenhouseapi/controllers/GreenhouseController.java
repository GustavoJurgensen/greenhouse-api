package com.cythr.greenhouseapi.controllers;

import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.models.GreenhouseData;
import com.cythr.greenhouseapi.repositories.GreenhouseDataRepository;
import com.cythr.greenhouseapi.repositories.GreenhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    @GetMapping(value = "/data/", produces = "application/json")
    public ResponseEntity<List<GreenhouseData>> greenhouseDataList(){
        List<GreenhouseData> list = (List<GreenhouseData>)greenhouseDataRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Greenhouse> add(@RequestBody Greenhouse greenhouse){
        Greenhouse gh = greenhouseRepository.save(greenhouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/data/", produces = "application/json")
    public ResponseEntity<GreenhouseData> add(@RequestBody GreenhouseData greenhouseData){
        Date date = new Date();
        Timestamp tm = new Timestamp(date.getTime());
        greenhouseData.setDate(tm);
        GreenhouseData gh = greenhouseDataRepository.save(greenhouseData);
        return new ResponseEntity<>(gh, HttpStatus.OK);
    }
}
