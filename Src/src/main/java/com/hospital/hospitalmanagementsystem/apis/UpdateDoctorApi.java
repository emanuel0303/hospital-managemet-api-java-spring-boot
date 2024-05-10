package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UpdateDoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public UpdateDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void updateDoctor(Integer drid, Doctor doctor) {
        // Call the appropriate method in the PatientService to update the patient
        doctorService.updateDoctorById(drid, doctor);
    }
}
