package com.hospital.hospitalmanagementsystem.service.impl;


import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Appointment;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.exception.DoctorNotFoundException;
import com.hospital.hospitalmanagementsystem.repository.AppointmentRepository;
import com.hospital.hospitalmanagementsystem.repository.DoctorRepository;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             ModelMapper modelMapper,
                             AppointmentRepository appointmentRepository) {

        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.appointmentRepository = appointmentRepository;
    }



    @Override
    public List<Doctor> getDoctors() {
        return  doctorRepository.findAll();
    }

    @Override
    public Doctor addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        updateDutyHrs(doctor); // Calculate and set duty hours
        doctorRepository.save(doctor);
        return doctor;
    }
    @Override
    @Transactional
    public void deleteDoctor(Integer doctorId) {
        // Retrieve doctor by ID
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));

        // Retrieve appointments associated with the doctor
        List<Appointment> appointments = appointmentRepository.findByDoctorDrid(doctorId);

        // Delete appointments associated with the doctor
        appointmentRepository.deleteAll(appointments);

        // Delete the doctor
        doctorRepository.delete(doctor);
    }

    @Override
    public String updateDoctorById(Integer drid, Doctor doctor) {
        // Check whether Doctor is in Database or Not
        doctorRepository.findById(drid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Doctor id " + drid));

        doctor.setDrid(drid);

        doctorRepository.save(doctor);
        return "Doctor with ID " + drid + " Updated successfully.";
    }

    @Override
    public String updateDoctor(Integer drid, DoctorDTO doctorDTO) {
        // Check whether the Doctor is in the database or not
        Doctor doctor = doctorRepository.findById(drid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Doctor id " + drid));

        // Map data from DTO to entity
        modelMapper.map(doctorDTO, doctor);

        doctorRepository.save(doctor);

        return "Doctor Updated Successfully";
    }

    @Override
    public String getDoctorNameById(Integer drid) {
        Doctor doctor = doctorRepository.findById(drid).orElse(null);
        return (doctor != null) ? doctor.getDoctorname() : null;
    }

    // Helper method to map Doctor entity to DoctorDTO
    private DoctorDTO mapDoctorToDTO(Doctor doctor) {
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setDutyhrs(getDutyhrs(doctor)); // Set duty hours
        return doctorDTO;
    }

    // Calculate duty hours based on start_time and end_time
    private Integer getDutyhrs(Doctor doctor) {
        if (doctor.getStart_time() != null && doctor.getEnd_time() != null) {
            long hours = ChronoUnit.HOURS.between(doctor.getStart_time(), doctor.getEnd_time());
            return Math.toIntExact(hours);
        } else {
            return null;
        }
    }

    // Update duty hours before saving or updating the entity
    @PrePersist
    @PreUpdate
    private void updateDutyHrs(Doctor doctor) {
        Integer dutyhrs = getDutyhrs(doctor);
        doctor.setDutyhrs(dutyhrs);
    }

}
