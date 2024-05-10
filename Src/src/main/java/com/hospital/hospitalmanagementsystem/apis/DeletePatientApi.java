package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientApi {

    private final PatientService patientService;

    @Autowired
    public DeletePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public void deletePatientById(Integer id) {
        patientService.deletePatient(id);
    }
}
