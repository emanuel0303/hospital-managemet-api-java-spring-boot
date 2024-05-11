package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddDoctorApi {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddDoctorApi(DoctorService doctorService,
                        ModelMapper modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.addDoctor(doctorDTO);
        String successMessage = "Doctor added successfully.\n" +
                "Doctor Name: " + doctor.getDoctorname() + "\n" +
                "Doctor Age: " + doctor.getDrage() + "\n" +
                "Doctor Email: " + doctor.getDremail();
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }
}