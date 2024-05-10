package com.hospital.hospitalmanagementsystem.controller;


import com.hospital.hospitalmanagementsystem.apis.*;
import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String addDoctor(@RequestBody Doctor doctor) {
        return addDoctorApi.addDoctor(doctor);
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
        updateDoctorApi.updateDoctor(drid, doctor);
        String message = "Doctor with ID " + drid + " updated successfully.";

        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/delete/{drid}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer drid) {
        deleteDoctorApi.deleteDoctorById(drid);
        String message = "Doctor with ID " + drid + " deleted successfully.";
        return ResponseEntity.ok().body(message);
    }

    @PatchMapping("/update/{drid}")
    public ResponseEntity<String> patchDoctor(@PathVariable Integer drid, @RequestBody DoctorDTO doctorDTO) {
        patchUpdateDoctorApi.updateDoctor(drid, doctorDTO);

        String message = "Doctor with ID " + drid + " updated successfully.";
        return ResponseEntity.ok().body(message);
    }
}
