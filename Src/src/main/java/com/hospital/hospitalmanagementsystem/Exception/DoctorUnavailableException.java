package com.hospital.hospitalmanagementsystem.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DoctorUnavailableException extends RuntimeException {
    public DoctorUnavailableException(String message) {
        super(message);
    }
}
