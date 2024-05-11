package com.hospital.hospitalmanagementsystem.apis;

import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BookAppointmentApi {
    private final PatientService patientService;
    private final DoctorService doctorService;

    public BookAppointmentApi(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public ResponseEntity<String> bookAppointment(Integer id, Integer drid, String date, String time) {
        try {
            // Retrieve patient and doctor names
            Patient patient = patientService.getPatientById(id);
            String patientName = patient.getPatientname();
            String doctorName = doctorService.getDoctorNameById(drid);

            // Delegate the appointment booking logic to the PatientService
            String appointmentEndTime = patientService.bookAppointment(id, drid, date, time);

            // Return success response with appointment details
            String successMessage = "Appointment booked successfully.\n" +
                    "Patient Name: " + patientName + "\n" +
                    "Doctor Name: " + doctorName + "\n" +
                    "Appointment Date: " + date + "\n" +
                    "Appointment Time: " + time + "\n" +
                    "Appointment End Time: " + appointmentEndTime;
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            // Return error response if booking fails
            return ResponseEntity.badRequest().body("Failed to book appointment: " + e.getMessage());
        }
    }
}
