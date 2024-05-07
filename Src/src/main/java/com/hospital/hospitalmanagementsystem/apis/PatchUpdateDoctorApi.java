package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatchUpdateDoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public PatchUpdateDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void updateDoctor(Integer drid, DoctorDTO doctorDTO) {
        doctorService.updateDoctor(drid, doctorDTO);
    }
}
