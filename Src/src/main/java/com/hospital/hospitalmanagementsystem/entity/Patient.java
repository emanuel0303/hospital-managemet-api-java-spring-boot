package com.hospital.hospitalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "Doctor_id")
    private Doctor doctor;




}
