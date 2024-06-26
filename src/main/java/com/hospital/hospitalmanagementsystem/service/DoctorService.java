package com.hospital.hospitalmanagementsystem.service;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;

import java.util.List;

public interface DoctorService {


    List<Doctor> getDoctors();
    
    Doctor addDoctor(DoctorDTO doctorDTO);

    void deleteDoctor(Integer drid);

    String updateDoctorById(Integer drid, Doctor doctor);

    String updateDoctor(Integer drid, DoctorDTO doctorDTO);


    String getDoctorNameById(Integer drid);
}
