package com.hospital.hospitalmanagementsystem.repository;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByDoctorDrid(Integer doctorId);
}
