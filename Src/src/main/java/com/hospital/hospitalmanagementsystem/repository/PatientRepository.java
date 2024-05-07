package com.hospital.hospitalmanagementsystem.repository;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
