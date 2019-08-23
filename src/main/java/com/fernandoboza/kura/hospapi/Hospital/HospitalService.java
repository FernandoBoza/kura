package com.fernandoboza.kura.hospapi.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.maps.errors.ApiException;

import static com.fernandoboza.kura.hospapi.Utils.Utils.createLatCord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Optional<Hospital> findById(String id) {
        int parsedInt = Integer.parseInt(id);
        return hospitalRepository.findById(parsedInt);
    }

    public Iterable<Hospital> findAllHospital() {
        return hospitalRepository.findAll();
    }


    public Iterable<Hospital> createHospital(List<Hospital> hospitals) throws InterruptedException, ApiException, IOException {
        List<Hospital> newHosp = new ArrayList<>();
        for (Hospital hosp : hospitals) {
            newHosp.add(createLatCord(hosp));
        }
        return hospitalRepository.saveAll(newHosp);
    }

    public Hospital updateHospital(String id, Hospital hospital) {
        if (hospitalRepository.existsById(Integer.parseInt(id))) {
            return hospitalRepository.save(hospital);
        } else {
            return null;
        }
    }

    public String deleteHospital(String id){
        Optional<Hospital> hospName = findById(id);
        var ref = new Object() {
            String name;
        };
        hospName.flatMap(hospital -> {
            ref.name = hospital.getName();
            return Optional.empty();
        });
        hospitalRepository.deleteById(Integer.parseInt(id));

        return "Deleted hospital " + ref.name + " | id : " + id;
    }

}
