package com.fernandoboza.kura.hospapi.Procedures;

import com.fernandoboza.kura.hospapi.Hospital.Hospital;
import com.fernandoboza.kura.hospapi.Hospital.HospitalRepository;
import static com.fernandoboza.kura.hospapi.Utils.Utils.getFromOptional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class ProceduresService {
    private HospitalRepository hospitalRepository;
    private final ProceduresRepository proceduresRepository;

    @Autowired
    public void HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Autowired
    public ProceduresService(ProceduresRepository proceduresRepository) {

        this.proceduresRepository = proceduresRepository;
    }

    public Optional<Procedures> findById(String hosp_id, String id) {
        int parsedInt = Integer.parseInt(id);
        Procedures newP = getFromOptional(proceduresRepository.findById(parsedInt));
        if (newP.getHospital() == Integer.parseInt(hosp_id)) {
            return proceduresRepository.findById(parsedInt);
        } else {
            return null;
//            TODO: Work on TRY CATCH for PROCEDURES THAT DONT EXIST
        }
    }

    public Iterable<Procedures> findAllProcedures(String hosp_id) {
        Iterable<Procedures> procedureLib = proceduresRepository.findAll();
        List<Procedures> hospProcedure = new ArrayList<>();
        for (Procedures p : procedureLib) {
            if (p.getHospital() == Integer.parseInt(hosp_id)) {
                hospProcedure.add(p);
            }
        }
        return hospProcedure;
    }


    public Iterable<Procedures> createProcedure(String hosp_id, List<Procedures> procedures) {
        Optional<Hospital> hosOpt = hospitalRepository.findById(Integer.parseInt(hosp_id));
        Hospital h;
        if (hosOpt.isPresent()) {
            h = hosOpt.get();

            for (Procedures p : procedures) {
                p.setHospital(Integer.parseInt(hosp_id));
            }

            h.setProcedures(procedures);
            return proceduresRepository.saveAll(procedures);
        } else {
            return null;
        }
    }

    public Procedures updateProcedures(String hosp_id, String id, Procedures procedure) {
        if (proceduresRepository.existsById(Integer.parseInt(id))) {
            return proceduresRepository.save(procedure);
        } else {
            return null;
        }
    }

    public String deleteProcedures(String hosp_id, String id) {
        Optional<Procedures> procedure = findById(hosp_id, id);
        var ref = new Object() {
            String name;
        };
        procedure.flatMap(p -> {
            ref.name = p.getName();
            return Optional.empty();
        });
        proceduresRepository.deleteById(Integer.parseInt(id));

        return "Deleted hospital " + ref.name + " | id : " + id;
    }
}
