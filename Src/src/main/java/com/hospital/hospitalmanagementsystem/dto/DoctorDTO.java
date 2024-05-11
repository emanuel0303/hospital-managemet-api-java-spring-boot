package com.hospital.hospitalmanagementsystem.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DoctorDTO {

    private Integer drid;

    @NotBlank(message = "Doctor name is mandatory")
    private String doctorname;

    @Min(value = 1, message = "Doctor age must be at least 1")
    private Integer drage;

    @NotBlank(message = "Address is mandatory")
    private String draddress;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email Should be valid")
    private String dremail;

    @NotNull(message = "Start time is mandatory")
    private LocalTime start_time;

    @NotNull(message = "End time is mandatory")
    private LocalTime end_time;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @Min(value = 1, message = "Max appointments must be at least 1")
    private Integer maxAppointments;

    private Integer dutyhrs;
}
