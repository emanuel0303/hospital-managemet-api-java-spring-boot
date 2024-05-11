package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddPatientApi {

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddPatientApi(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> addPatient(PatientDTO patientDTO) {
        Patient patient = patientService.addPatient(patientDTO);
        String successMessage = "Patient Added successfully.\n" +
                "Patient ID: " + patient.getId() + "\n" +
                "Patient Name: " + patient.getPatientname() + "\n" +
                "Doctor Name: " + patient.getDoctor().getDoctorname(); // Assuming you have a Doctor object in Patient
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}
