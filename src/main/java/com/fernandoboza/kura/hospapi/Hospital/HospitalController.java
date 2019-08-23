package com.fernandoboza.kura.hospapi.Hospital;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.fernandoboza.kura.hospapi.Utils.Utils.createLatCord;

@RestController
@RequestMapping("/hospitals/v1/hosp/")
@CrossOrigin // For DEV Angular and Spring app locally REMOVE FOR PRODUCTION
public class HospitalController {
    @Autowired
    private HospitalRepository hospitalRepository;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Hospital> createHospital(@RequestBody List<Hospital> hospitals) throws InterruptedException, ApiException, IOException {
        List<Hospital> newHosp = new ArrayList<>();
        for (Hospital hosp: hospitals){
            newHosp.add(createLatCord(hosp));
        }
        return hospitalRepository.saveAll(newHosp);
    }

    @GetMapping(path="")
    public Iterable<Hospital> findAllHospital() {
        return hospitalRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Hospital> findById(@PathVariable String id){
        int parsedInt = Integer.parseInt(id);
        return hospitalRepository.findById(parsedInt);
    }
}