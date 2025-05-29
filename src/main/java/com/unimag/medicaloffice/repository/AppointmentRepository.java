package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
    SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
    FROM Appointment a
    WHERE a.consultRoom.id = :consultRoomId
      AND a.status = 'SCHEDULED'
      AND :newStartTime < a.endTime
      AND :newEndTime > a.startTime
""")
    Boolean existsConflictInConsultRoom(
            @Param("consultRoomId") Long consultRoomId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime
    );

    @Query("""
    SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
    FROM Appointment a
    WHERE a.doctor.id = :doctorId
      AND a.status = 'SCHEDULED'
      AND :newStartTime < a.endTime
      AND :newEndTime > a.startTime
""")
    Boolean existsConflictInDoctorSchedule(
            @Param("doctorId") Long doctorId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime
    );

    @Query("""
    SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
    FROM Appointment a
    WHERE a.patient.id = :patientId
      AND a.status = 'SCHEDULED'
      AND :newStartTime < a.endTime
      AND :newEndTime > a.startTime
""")
    Boolean existsConflictInPatientSchedule(
            @Param("patientId") Long patientId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime
    );

    @Query("""
    SELECT COUNT(a) > 0
    FROM Appointment a
    WHERE a.id <> :appointmentId
      AND a.doctor.id = :doctorId
      AND a.startTime < :endTime
      AND a.endTime > :startTime
""")
    boolean existsDoctorConflictExcludingAppointment(
            Long doctorId, LocalDateTime startTime, LocalDateTime endTime, Long appointmentId);


    @Query("""
        SELECT COUNT(a) > 0
        FROM Appointment a
        WHERE a.id <> :appointmentId
          AND a.consultRoom.id = :consultRoomId
          AND a.startTime < :endTime
          AND a.endTime > :startTime
    """)
    boolean existsConsultRoomConflictExcludingAppointment(
            @Param("consultRoomId") Long consultRoomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("appointmentId") Long appointmentId
    );

    @Query("""
        SELECT COUNT(a) > 0
        FROM Appointment a
        WHERE a.id <> :appointmentId
          AND a.patient.id = :patientId
          AND a.startTime < :endTime
          AND a.endTime > :startTime
    """)
    boolean existsPatientConflictExcludingAppointment(
            @Param("patientId") Long patientId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("appointmentId") Long appointmentId
    );

    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.startTime BETWEEN :startOfDay AND :endOfDay")
    List<Appointment> findAppointmentsByDoctorIdAndDate(
            @Param("doctorId") Long doctorId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

}
