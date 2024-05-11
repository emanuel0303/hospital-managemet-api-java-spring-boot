package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatchUpdatePatientApi {

    private final PatientService patientService;

    @Autowired
    public PatchUpdatePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<String> updateDisease(Integer id, PatientDTO patientDTO) {
        patientService.updateDisease(id, patientDTO);
        return null;
    }
}
