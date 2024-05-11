package com.hospital.hospitalmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue
    private Integer drid;
    private String doctorname;
    private Integer drage;
    private String draddress;
    private String dremail;
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalDate date;

    private Integer dutyhrs;
    private Integer maxAppointments;


    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;

}
