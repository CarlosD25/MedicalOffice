package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
