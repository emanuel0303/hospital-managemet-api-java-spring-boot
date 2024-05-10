package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePatientApi {

    private final PatientService patientService;

    @Autowired
    public UpdatePatientApi(PatientService patientService) {
        this.patientService = patientService;
    }

    public void updatePatient(Integer id, Patient patient) {
        // Call the appropriate method in the PatientService to update the patient
        patientService.updatePatientById(id, patient);
    }
}
