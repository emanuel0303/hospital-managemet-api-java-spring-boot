package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteDoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public DeleteDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<String> deleteDoctorById(Integer drid) {
        doctorService.deleteDoctor(drid);
        String message = "Doctor with ID " + drid + " deleted successfully.";
        return ResponseEntity.ok().body(message);
    }
}
