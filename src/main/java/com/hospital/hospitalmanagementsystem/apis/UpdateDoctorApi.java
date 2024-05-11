package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateDoctorApi {

    private final DoctorService doctorService;

    @Autowired
    public UpdateDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<String> updateDoctor(Integer drid, Doctor doctor) {
        doctorService.updateDoctorById(drid, doctor);
        String message = "Doctor with ID " + drid + " updated successfully.";
        return ResponseEntity.ok().body(message);
    }
}
