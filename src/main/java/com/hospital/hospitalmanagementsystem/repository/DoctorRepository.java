package com.hospital.hospitalmanagementsystem.repository;

import com.hospital.hospitalmanagementsystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    long countAppointmentsByDridAndDate(Integer drid, LocalDate parse);
}
