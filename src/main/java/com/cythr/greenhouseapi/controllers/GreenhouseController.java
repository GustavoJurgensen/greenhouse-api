package com.cythr.greenhouseapi.controllers;

import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.repositories.GreenhouseDataRepository;
import com.cythr.greenhouseapi.repositories.GreenhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller to Greenhouse Requests HTTP
 */
@RestController
@RequestMapping("/greenhouse")
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class GreenhouseController {
    /**
     * Reference Greenhouse table
     */
    @Autowired
    private GreenhouseRepository greenhouseRepository;
    /**
     * Reference Greenhouse Data table
     */
    @Autowired
    private GreenhouseDataRepository greenhouseDataRepository;

    /**
     * Get all data from greenhouse table
     * @return List<Greenhouse>
     */
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Greenhouse>> getGreenhouseList(){
        final List<Greenhouse> list = (List<Greenhouse>)greenhouseRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    /**
     * Get greenhouse by address from greenhouse table
     * @return Greenhouse
     */
    @GetMapping(value = "/{addr}", produces = "application/json")
    public ResponseEntity<Greenhouse> findByAddr(@PathVariable ("addr") final String addr){
        final Greenhouse greenhouse = greenhouseRepository.findByAddr(addr);
        return new ResponseEntity<>(greenhouse, HttpStatus.OK);
    }

    /**
     * Insert object on table greenhouse
     * @param greenhouse Greenhouse object
     * @return Greenhouse
     */
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Greenhouse> add(@RequestBody final Greenhouse greenhouse){
        return new ResponseEntity<>(greenhouseRepository.save(greenhouse), HttpStatus.OK);
    }

    /**
     * Update object on table greenhouse
     * @param greenhouse Greenhouse object
     * @return Greenhouse
     */
    @SuppressWarnings("PMD.LawOfDemeter")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Greenhouse> update(@RequestBody final Greenhouse greenhouse) {
        if (greenhouse == null || greenhouse.getId() == null) {
            throw new InvalidRequestException("Greenhouse object or ID must not be null!");
        }
        final Optional<Greenhouse> lGreenhouse = greenhouseRepository.findById(greenhouse.getId());
        if (!lGreenhouse.isPresent()) {
            throw new InvalidRequestException("Greenhouse Not Found!");
        }
        Greenhouse existingGh = lGreenhouse.get();
        existingGh.setAddr(greenhouse.getAddr());
        existingGh.setCropType(greenhouse.getCropType());
        existingGh.settHumidity(greenhouse.gettHumidity());
        existingGh.settLuminosity(greenhouse.gettLuminosity());
        existingGh.settMoisture(greenhouse.gettMoisture());
        existingGh.settTemperature(greenhouse.gettTemperature());

        return new ResponseEntity<>(greenhouseRepository.save(existingGh), HttpStatus.OK);
    }

    /**
     * Delete Greenhouse and your soons from every table
     * @param addr Greenhouse addr
     * @return Greenhouse
     */
    @DeleteMapping(value = "/{addr}", produces = "application/json")
    public ResponseEntity<Greenhouse> deleteByAddr(@PathVariable ("addr") final String addr){
        final Greenhouse greenhouse = greenhouseRepository.findByAddr(addr);
        if(greenhouse == null){
            throw new InvalidRequestException("Greenhouse Not Found!");
        }
        greenhouseRepository.deleteByAddr(addr);
        greenhouseDataRepository.deleteByAddr(addr);
        return new ResponseEntity<>(greenhouse, HttpStatus.OK);
    }

    /**
     * Delete greenhouse by ID
     * @param id Greenhouse ID
     * @return Greenhouse
     */
    @DeleteMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Greenhouse> deleteById(@PathVariable ("id") final String id){
        final Optional<Greenhouse> greenhouse = greenhouseRepository.findById(Long.valueOf(id));
        if(!greenhouse.isPresent()){
            throw new InvalidRequestException("Greenhouse Not Found!");
        }
        greenhouseRepository.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Return Bad response to wrong http requests
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidRequestException extends RuntimeException {
        private static final long serialVersionUID = 50L;
        /**
         * Constructor for Bad Requests
         * @param str Message
         */
        public InvalidRequestException(final String str) {
            super(str);
        }
    }
}
