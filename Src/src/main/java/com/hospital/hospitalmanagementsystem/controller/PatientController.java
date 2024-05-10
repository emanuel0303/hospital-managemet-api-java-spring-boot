package com.hospital.hospitalmanagementsystem.controller;


import com.hospital.hospitalmanagementsystem.apis.*;
import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.entity.AppointmentRequest;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")

public class PatientController {

    private final AddPatientApi addPatientApi;
    private final PatientService patientService;
    private final GetPatientApi getPatientApi;
    private final UpdatePatientApi updatePatientApi;
    private final DeletePatientApi deletePatientApi;
    private final PatchUpdatePatientApi patchUpdatePatientApi;
    private final BookAppointmentApi bookAppointmentApi;

    @Autowired
    public PatientController(AddPatientApi addPatientApi,
                             PatientService patientService,
                             GetPatientApi getPatientApi,
                             UpdatePatientApi updatePatientApi,
                             DeletePatientApi deletePatientApi,
                             PatchUpdatePatientApi patchUpdatePatientApi,
                             BookAppointmentApi bookAppointmentApi) {
        this.addPatientApi = addPatientApi;
        this.patientService = patientService;
        this.getPatientApi = getPatientApi;
        this.updatePatientApi = updatePatientApi;
        this.deletePatientApi = deletePatientApi;
        this.patchUpdatePatientApi = patchUpdatePatientApi;
        this.bookAppointmentApi = bookAppointmentApi;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        return addPatientApi.addPatient(patient);
    }

    @GetMapping()
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/get")
    public Patient getPatient(@RequestParam Integer id) {
        return getPatientApi.getPatientById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatientById(@PathVariable Integer id, @RequestBody Patient patient) {
        updatePatientApi.updatePatient(id, patient);
        String message = "Patient with ID " + id + " updated successfully.";

        return ResponseEntity.ok().body(message);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Integer id) {
        deletePatientApi.deletePatientById(id);

        String message = "Patient with ID " + id + " deleted successfully.";
        return ResponseEntity.ok().body(message);
    }

    @PatchMapping("/update-disease/{id}")
    public ResponseEntity<String> updateDisease(@PathVariable Integer id, @RequestBody PatientDTO patientDTO) {
        patchUpdatePatientApi.updateDisease(id, patientDTO);

        String message = "Patient with ID " + id + " updated successfully.";
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request) {
        try {
            // Use the BookAppointmentApi class to book the appointment
            bookAppointmentApi.bookAppointment(request.getId(), request.getDrid(), request.getDate(), request.getTime());

            return ResponseEntity.ok("Appointment booked successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
