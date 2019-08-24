package com.fernandoboza.kura.hospapi.Procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospitals/v1/hosp/")
@CrossOrigin
public class ProceduresController {
    @Autowired
    private ProceduresService proceduresService;

    @PostMapping(path = "{hosp_id}/procedures", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Procedures> createProcedure(@PathVariable String hosp_id, @RequestBody List<Procedures> procedures) {
       return proceduresService.createProcedure(hosp_id, procedures);
    }

    @GetMapping(path = "{hosp_id}/procedures")
    public Iterable<Procedures> findAllProcedures(@PathVariable String hosp_id) {
        return proceduresService.findAllProcedures(hosp_id);
    }

    @GetMapping(path = "{hosp_id}/procedures/{id}")
    public Optional<Procedures> findById(@PathVariable String hosp_id ,@PathVariable String id) {
        return proceduresService.findById(hosp_id, id);
    }

    @PutMapping(path = "{hosp_id}/procedures/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
        public Procedures updateProcedures(@PathVariable String hosp_id ,@PathVariable String id, @RequestBody Procedures procedure) {
        return proceduresService.updateProcedures(hosp_id, id, procedure);
    }

    @DeleteMapping(path = "{hosp_id}/procedures/{id}")
    public String deleteHospital(@PathVariable String hosp_id ,@PathVariable String id){
        return proceduresService.deleteProcedures(hosp_id, id);
    }
}
