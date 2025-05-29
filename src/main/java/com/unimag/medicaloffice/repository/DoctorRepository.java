package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT CASE WHEN :startTime >= d.availableFrom AND :endTime <= d.availableTo THEN true ELSE false END " +
            "FROM Doctor d WHERE d.id = :doctorId")
    Boolean isDoctorAvailable(@Param("doctorId") Long doctorId,
                              @Param("startTime") LocalTime startTime,
                              @Param("endTime") LocalTime endTime);

    List<Doctor> findBySpecialtyIgnoreCase(String specialty);



}
