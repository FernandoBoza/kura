package com.fernandoboza.kura.hospapi.Procedures;

import com.fernandoboza.kura.hospapi.Hospital.Hospital;
import com.fernandoboza.kura.hospapi.Hospital.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospitals/v1/hosp/")
@CrossOrigin
public class ProceduresController {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ProceduresRepository proceduresRepository;

    @PostMapping(path = "{hosp_id}/procedures", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Procedures> createProcedure(@PathVariable String hosp_id, @RequestBody List<Procedures> procedures) {
        Optional<Hospital> hosOpt = hospitalRepository.findById(Integer.parseInt(hosp_id));
        Hospital h = new Hospital();
        if (hosOpt.isPresent()) {
            h = hosOpt.get();

            for (Procedures p : procedures) {
//            p.setHospital(h);
//            System.out.println(p);
            }
            h.setProcedures(procedures);
            return proceduresRepository.saveAll(procedures);
        } else {
            return null;
        }

    }
}
