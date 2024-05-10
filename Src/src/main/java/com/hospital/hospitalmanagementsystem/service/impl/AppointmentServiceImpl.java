package com.hospital.hospitalmanagementsystem.service.impl;

import com.hospital.hospitalmanagementsystem.entity.Appointment;
import com.hospital.hospitalmanagementsystem.exception.AppointmentNotFoundException;
import com.hospital.hospitalmanagementsystem.repository.AppointmentRepository;
import com.hospital.hospitalmanagementsystem.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));

        appointmentRepository.delete(appointment);
    }

    @Override
    @Transactional
    public void deleteAppointmentsByDoctorId(Integer drid) {
        // Retrieve appointments associated with the specified doctor ID
        List<Appointment> appointments = appointmentRepository.findByDoctorDrid(drid);

        // Delete all appointments in a single bulk operation
        appointmentRepository.deleteAll(appointments);
    }
}