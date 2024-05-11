package com.hospital.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientname;
    private Integer age;
    private String address;
    private String email;
    private String disease;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Doctor_id", referencedColumnName = "drid")
    private Doctor doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments;




}
