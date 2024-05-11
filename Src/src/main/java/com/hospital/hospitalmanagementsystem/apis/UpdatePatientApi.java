package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientApi {

    private final PatientService patientService;

    @Autowired
    public UpdatePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public ResponseEntity<String> updatePatient(Integer id, Patient patient) {
        patientService.updatePatientById(id, patient);
        String message = "Patient with ID " + id + " updated successfully.";
        return ResponseEntity.ok().body(message);
    }
}
