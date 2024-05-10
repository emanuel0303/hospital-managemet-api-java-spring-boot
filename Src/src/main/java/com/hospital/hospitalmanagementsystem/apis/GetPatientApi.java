package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GetPatientApi {

    private final PatientRepository patientRepository;

    @Autowired
    public GetPatientApi(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatientById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }
}
