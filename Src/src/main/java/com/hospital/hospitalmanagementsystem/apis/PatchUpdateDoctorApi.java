package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PatchUpdateDoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public PatchUpdateDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<String> updateDoctor(Integer drid, DoctorDTO doctorDTO) {
        doctorService.updateDoctor(drid, doctorDTO);
        String message = "Doctor with ID " + drid + " updated successfully.";
        return ResponseEntity.ok().body(message);
    }
}
