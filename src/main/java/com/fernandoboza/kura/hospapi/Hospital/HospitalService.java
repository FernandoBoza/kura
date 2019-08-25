package com.fernandoboza.kura.hospapi.Hospital;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.IOException;
import java.util.*;

import static com.fernandoboza.kura.hospapi.Utils.Utils.*;

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
        Hospital h = getFromOptional(findById(id));
        String name = h.getName();
        hospitalRepository.deleteById(Integer.parseInt(id));
        return "Deleted hospital " + name + " | id : " + id;
    }

//    @Query("SELECT h FROM hospital h where h.lat = ?1 And h.lng = ?2")
    @Query("SELECT *, ( 6371 * acos( cos( radians(?1) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians( ?2) ) + sin( radians(?1) ) * sin( radians( lat ) ) ) ) AS distance FROM hospital HAVING distance < 8.5 ORDER BY distance LIMIT 0 , 20")
    public List<Hospital> findHospitalByZipcode(String zipcode) throws InterruptedException, ApiException, IOException {
        double[] coords = ZipodePoint(zipcode);
        return hospitalRepository.findByLatAndLng(coords[0], coords[1]);
    }
}
