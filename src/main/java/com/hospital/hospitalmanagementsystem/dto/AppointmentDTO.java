package com.hospital.hospitalmanagementsystem.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    private Integer patientId;
    private Integer doctorId;
    private String date;
    private String time;

}
