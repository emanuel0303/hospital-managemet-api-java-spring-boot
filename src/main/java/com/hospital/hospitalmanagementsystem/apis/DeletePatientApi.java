package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientApi {

    private final PatientService patientService;

    @Autowired
    public DeletePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<String> deletePatientById(Integer id) {
        patientService.deletePatient(id);
        String message = "Patient with ID " + id + " deleted successfully.";
        return ResponseEntity.ok().body(message);
    }
}
