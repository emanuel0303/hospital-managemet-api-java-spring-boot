package com.hospital.hospitalmanagementsystem.controller;


import com.hospital.hospitalmanagementsystem.apis.*;
import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")

public class DoctorController {

    private final AddDoctorApi addDoctorApi;
    private final DoctorService doctorService;
    private final DeleteDoctorApi deleteDoctorApi;
    private final UpdateDoctorApi updateDoctorApi;
    private final PatchUpdateDoctorApi patchUpdateDoctorApi;
    private final GetDoctorApi getDoctorApi;
    @Autowired
    public DoctorController(
            AddDoctorApi addDoctorApi,
            DoctorService doctorService,
            DeleteDoctorApi deleteDoctorApi,
            UpdateDoctorApi updateDoctorApi,
            PatchUpdateDoctorApi patchUpdateDoctorApi,
            GetDoctorApi getDoctorApi) {
        this.addDoctorApi = addDoctorApi;
        this.doctorService = doctorService;
        this.getDoctorApi = getDoctorApi;
        this.deleteDoctorApi = deleteDoctorApi;
        this.updateDoctorApi = updateDoctorApi;
        this.patchUpdateDoctorApi = patchUpdateDoctorApi;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@Validated @RequestBody DoctorDTO doctorDTO, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, construct error message and return bad request response
            String errorMessage = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .findFirst()
                    .orElse("Validation failed");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        // If validation passes, proceed with adding the patient
        return addDoctorApi.addDoctor(doctorDTO);
    }

    @GetMapping()
    public List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @GetMapping("/get")
    public Doctor getDoctor(@RequestParam Integer drid) {
        return getDoctorApi.getDoctor(drid);
    }

    @PutMapping("/update/{drid}")
    public ResponseEntity<String> updateDoctor(@PathVariable Integer drid, @RequestBody Doctor doctor) {
       return updateDoctorApi.updateDoctor(drid, doctor);
    }

    @DeleteMapping("/delete/{drid}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer drid) {
        return deleteDoctorApi.deleteDoctorById(drid);

    }

    @PatchMapping("/update/{drid}")
    public ResponseEntity<String> patchDoctor(@PathVariable Integer drid, @RequestBody DoctorDTO doctorDTO) {
        return patchUpdateDoctorApi.updateDoctor(drid, doctorDTO);
    }
}
