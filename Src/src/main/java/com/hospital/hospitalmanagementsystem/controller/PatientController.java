package com.hospital.hospitalmanagementsystem.controller;


import com.hospital.hospitalmanagementsystem.apis.*;
import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.entity.AppointmentRequest;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<String> addPatient(@Validated @RequestBody PatientDTO patientDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // If validation passes, proceed with adding the patient
        return addPatientApi.addPatient(patientDTO);
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
        return updatePatientApi.updatePatient(id, patient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Integer id) {
        return deletePatientApi.deletePatientById(id);
    }

    @PatchMapping("/update-disease/{id}")
    public ResponseEntity<String> updateDisease(@PathVariable Integer id, @RequestBody PatientDTO patientDTO) {
        return patchUpdatePatientApi.updateDisease(id, patientDTO);
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request) {
        return bookAppointmentApi.bookAppointment(request.getId(), request.getDrid(), request.getDate(), request.getTime());
    }

}
