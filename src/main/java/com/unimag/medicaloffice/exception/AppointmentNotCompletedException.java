package com.unimag.medicaloffice.exception;

public class AppointmentNotCompletedException extends RuntimeException {
    public AppointmentNotCompletedException(String message) {
        super(message);
    }
}
