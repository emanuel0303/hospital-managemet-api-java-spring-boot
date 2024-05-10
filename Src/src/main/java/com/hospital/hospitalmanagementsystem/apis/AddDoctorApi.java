package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDoctorApi {

    private final DoctorService doctorService;

    @Autowired
    public AddDoctorApi(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public String addDoctor(Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "Doctor Added successfully";
    }
}
