package com.fernandoboza.kura.hospapi.Hospital;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospitals/v1/hosp/")
@CrossOrigin // For DEV Angular and Spring app locally REMOVE FOR PRODUCTION
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Hospital> createHospital(@RequestBody List<Hospital> hospitals) throws InterruptedException, ApiException, IOException {
        return hospitalService.createHospital(hospitals);
    }

    @GetMapping(path = "")
    public Iterable<Hospital> findAllHospital() {
        return hospitalService.findAllHospital();
    }

    @GetMapping(path = "{id}")
    public Optional<Hospital> findById(@PathVariable String id) {
        return hospitalService.findById(id);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Hospital updateHospital(@PathVariable String id, @RequestBody Hospital hospital) {
        return hospitalService.updateHospital(id, hospital);
    }

    @DeleteMapping(path = "{id}")
    public String deleteHospital(@PathVariable String id){
      return hospitalService.deleteHospital(id);
    }

    @GetMapping(path = "search/{zip}_{radius}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Hospital> findHospitalByZipcode(@PathVariable String zip, @PathVariable String radius) throws InterruptedException, ApiException, IOException {
        return hospitalService.findHospitalByZipcode(zip, radius);
    }
}

