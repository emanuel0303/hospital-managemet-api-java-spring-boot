package com.hospital.hospitalmanagementsystem.service;

public interface AppointmentService {
    void deleteAppointment(Long appointmentId);

    void deleteAppointmentsByDoctorId(Integer drid);
}
