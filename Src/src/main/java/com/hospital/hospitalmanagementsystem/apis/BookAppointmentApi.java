package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BookAppointmentApi {
    // Declare an instance variable for PatientService
    private final PatientService patientService;

    // Constructor for BookAppointmentApi class to inject PatientService
    @Autowired
    public BookAppointmentApi(PatientService patientService) {
        this.patientService = patientService;
    }

    // Method to book appointment
    public ResponseEntity<String> bookAppointment(Integer id, Integer drid, String date, String time) {
        // Delegate the appointment booking logic to the PatientService
        patientService.bookAppointment(id, drid, date, time);
        String message = "Appointment booked successfully.";
        return ResponseEntity.ok().body(message);
    }
}
