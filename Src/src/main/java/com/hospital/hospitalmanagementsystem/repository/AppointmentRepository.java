package com.hospital.hospitalmanagementsystem.repository;

import com.hospital.hospitalmanagementsystem.entity.Appointment;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    long countAppointmentsByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);

    boolean existsByPatientAndDoctorAndAppointmentDate(Patient patient, Doctor doctor, LocalDate appointmentDate);

    boolean existsByAppointmentDateAndAppointmentTime(LocalDate appointmentDate, LocalTime appointmentTime);
}
