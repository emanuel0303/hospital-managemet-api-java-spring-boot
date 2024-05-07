package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDoctorApi {
    private final DoctorService doctorService;

    @Autowired
    public DeleteDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void deleteDoctorById(Integer drid) {
        doctorService.deleteDoctor(drid);
    }
}
