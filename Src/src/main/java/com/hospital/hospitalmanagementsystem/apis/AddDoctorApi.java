package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDoctorApi {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @Autowired
    public AddDoctorApi(DoctorService doctorService, ModelMapper modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    public String addDoctor(DoctorDTO doctorDTO) {
        // Map DTO to entity
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

        // Add doctor using the service
        doctorService.addDoctor(doctorDTO);

        return "Doctor Added successfully";
    }
}
