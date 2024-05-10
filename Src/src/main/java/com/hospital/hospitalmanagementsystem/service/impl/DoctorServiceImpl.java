package com.hospital.hospitalmanagementsystem.service.impl;


import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.repository.DoctorRepository;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {

        this.doctorRepository = doctorRepository;
    }



    @Override
    public List<Doctor> getDoctors() {
        return  doctorRepository.findAll();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public String deleteDoctor(Integer drid) {
        // Check whether the doctor is in the database or not
        Doctor doctor = doctorRepository
                .findById(drid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Doctor id " + drid));

        doctorRepository.delete(doctor);
        return "Doctor with ID " + drid + " deleted successfully.";
    }

    @Override
    public String updateDoctorById(Integer drid, Doctor doctor) {
        //Check whether Doctor is in Database or Not
        doctorRepository
                .findById(drid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Doctor id " + drid));

        doctor.setDrid(drid);

        doctorRepository.save(doctor);
        return "Doctor with ID " + drid + " Updated successfully.";
    }

    @Override
    public String updateDoctor(Integer drid, DoctorDTO doctorDTO) {
        // Check whether the Doctor is in the database or not
        Doctor doctor = doctorRepository
                .findById(drid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Doctor id " + drid));

        doctor.setDremail(doctorDTO.getDremail()); // Assuming getDremail() returns String

        doctorRepository.save(doctor);

        return "Doctor Updated Successfully";
    }



}
