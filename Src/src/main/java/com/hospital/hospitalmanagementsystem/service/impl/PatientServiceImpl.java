package com.hospital.hospitalmanagementsystem.service.impl;

import com.hospital.hospitalmanagementsystem.dto.DoctorDTO;
import org.modelmapper.ModelMapper;
import com.hospital.hospitalmanagementsystem.dto.PatientDTO;
import com.hospital.hospitalmanagementsystem.entity.Appointment;
import com.hospital.hospitalmanagementsystem.entity.Doctor;
import com.hospital.hospitalmanagementsystem.entity.Patient;
import com.hospital.hospitalmanagementsystem.repository.AppointmentRepository;
import com.hospital.hospitalmanagementsystem.repository.PatientRepository;
import com.hospital.hospitalmanagementsystem.service.DoctorService;
import com.hospital.hospitalmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.hospital.hospitalmanagementsystem.repository.DoctorRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, DoctorService doctorService, AppointmentRepository appointmentRepository,
                              ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.doctorService = doctorService;
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Override
    public String deletePatient(Integer id) {
        // Check whether the patient is in the database or not
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Patient id " + id));

        patientRepository.delete(patient);
        return "Patient with ID " + id + " deleted successfully.";
    }

    @Override
    public String updateDisease(Integer id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Patient id " + id));

        // Map data from DTO to entity
        modelMapper.map(patientDTO, patient);

        patientRepository.save(patient);
        return "Patient Updated Successfully";
    }

    @Override
    public String updatePatientById(Integer id, Patient patient) {
        // Check whether patient exists in the database or not
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Patient id " + id));

        // Merge changes from the incoming Patient object to the existingPatient entity
        modelMapper.map(patient, existingPatient);

        // Ensure the ID remains the same
        existingPatient.setId(id);

        // Save the updated patient entity
        patientRepository.save(existingPatient);

        return "Patient with ID " + id + " Updated successfully.";
    }

    @Override
    public Patient addPatient(PatientDTO patientDTO) {
        // Map data from DTO to entity
        Patient patient = modelMapper.map(patientDTO, Patient.class);

        patientRepository.save(patient);
        return patient;
    }


    @Override
    public String bookAppointment(Integer id, Integer drid, String date, String time) {
        try {
            // Parse date and time
            LocalDate appointmentDate = LocalDate.parse(date);
            LocalTime appointmentTime = LocalTime.parse(time);

            // Check if the patient exists
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

            // Retrieve the doctor entity using the drid
            Doctor doctor = doctorRepository.findById(drid)
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + drid));

            // Map Doctor entity to DoctorDTO (if needed)
            DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);

            // Check if the appointment date is in the future
            if (appointmentDate.isBefore(LocalDate.now())) {
                throw new RuntimeException("Appointment date must be in the future.");
            }

            // Check if the appointment time falls within the doctor's working hours
            if (appointmentTime.isBefore(doctor.getStart_time()) || appointmentTime.isAfter(doctor.getEnd_time())) {
                throw new RuntimeException("Appointment time is outside of doctor's working hours.");
            }

            // Check if the doctor is available at the specified time
            if (!isDoctorAvailableAtTime(doctor, appointmentTime)) {
                throw new RuntimeException("Doctor is not available at the specified time.");
            }

            // Check if the maximum appointments limit is reached for the specified date
            if (isMaxAppointmentsReached(doctor, appointmentDate)) {
                throw new RuntimeException("Maximum appointments reached for the specified date.");
            }

            // Check if there are any existing appointments at the specified date and time
            if (isAppointmentSlotTaken(appointmentDate, appointmentTime)) {
                throw new RuntimeException("Appointment slot is already taken.");
            }

            // Check if the patient already has an appointment with the specified doctor on the given date
            if (isPatientAlreadyBooked(patient, doctor, appointmentDate)) {
                throw new RuntimeException("Patient already has an appointment with this doctor on the specified date.");
            }

            // Add buffer time (30 minutes) to the appointment duration
            LocalTime appointmentEndTime = appointmentTime.plusMinutes(30);

            // Create appointment entity
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor); // Associate the appointment with the doctor
            appointment.setAppointmentDate(appointmentDate);
            appointment.setAppointmentTime(appointmentTime);
            appointment.setAppointmentEndTime(appointmentEndTime); // Set appointment end time

            // Save the appointment details
            appointmentRepository.save(appointment);

            return appointmentEndTime.toString();

        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date or time format.");
        }
    }

    @Override
    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }


    private boolean isAppointmentSlotTaken(LocalDate appointmentDate, LocalTime appointmentTime) {
        // Check if there are any existing appointments at the specified date and time
        return appointmentRepository.existsByAppointmentDateAndAppointmentTime(appointmentDate, appointmentTime);
    }

    private boolean isMaxAppointmentsReached(Doctor doctor, LocalDate appointmentDate) {
        // Retrieve the count of appointments for the given doctor on the specified date
        long appointmentsCount = appointmentRepository.countAppointmentsByDoctorAndAppointmentDate(doctor, appointmentDate);

        // Get the maximum appointments allowed for the doctor
        int maxAppointments = doctor.getMaxAppointments();

        // Compare with the maximum appointments allowed
        return appointmentsCount >= maxAppointments;
    }

    private boolean isDoctorAvailableAtTime(Doctor doctor, LocalTime appointmentTime) {
        // Compare the appointment time with the doctor's working hours
        return appointmentTime.isAfter(doctor.getStart_time()) && appointmentTime.isBefore(doctor.getEnd_time());
    }


    private boolean isPatientAlreadyBooked(Patient patient, Doctor doctor, LocalDate appointmentDate) {
        // Check if the patient already has an appointment with the specified doctor on the given date
        return appointmentRepository.existsByPatientAndDoctorAndAppointmentDate(patient, doctor, appointmentDate);
    }

}
