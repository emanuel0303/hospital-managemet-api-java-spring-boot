package com.hospital.hospitalmanagementsystem.service;

import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.entity.Patient;

import java.util.List;

public interface PatientService {


    List<Patient> getPatients();

    void deletePatient(Integer id);

    String updateDisease(Integer id, PatientDTO patientDTO);

    String updatePatientById(Integer id, Patient patient);


    Patient addPatient(PatientDTO patientDTO);


    String bookAppointment(Integer id, Integer drid, String date, String time);

    Patient getPatientById(Integer id);
}
