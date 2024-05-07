package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddPatientApi {

    private final PatientService patientService;

    @Autowired
    public AddPatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        // Set the doctor for the patient
        Doctor doctor = patient.getDoctor();
        if (doctor != null) {
            patient.setDoctor(doctor);
        } else {
            throw new IllegalArgumentException("No doctor provided for the patient.");
        }
        patientService.addPatient(patient);
        return new ResponseEntity<>("Patient Added successfully", HttpStatus.CREATED);
    }

}
