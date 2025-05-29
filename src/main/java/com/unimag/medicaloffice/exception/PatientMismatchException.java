package com.unimag.medicaloffice.exception;

public class PatientMismatchException extends RuntimeException {
    public PatientMismatchException(String message) {
        super(message);
    }
}
