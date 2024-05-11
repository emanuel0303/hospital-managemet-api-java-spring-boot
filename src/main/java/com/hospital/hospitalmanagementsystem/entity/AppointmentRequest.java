package com.hospital.hospitalmanagementsystem.entity;


import lombok.Data;

@Data
public class AppointmentRequest {
    private Integer id;
    private Integer drid;
    private String date;
    private String time;


}