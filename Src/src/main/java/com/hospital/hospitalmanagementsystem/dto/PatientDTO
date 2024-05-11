package com.hospital.hospitalmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
public class PatientDTO {

    private Integer id;

    @NotBlank(message = "Patient name is mandatory")
    private String patientname;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Disease is mandatory")
    private String disease;

    private Integer drid;
}
