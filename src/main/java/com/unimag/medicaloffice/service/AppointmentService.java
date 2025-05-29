package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.dto.request.AppointmentRequestDTO;
import com.unimag.medicaloffice.dto.response.AppointmentResponseDTO;
import com.unimag.medicaloffice.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO);
    AppointmentResponseDTO get(Long id);
    List<AppointmentResponseDTO> getAll();
    AppointmentResponseDTO update(Long id,AppointmentRequestDTO appointmentRequestDTO);
    void delete(Long id);
    boolean isAppointmentValid(Appointment appointment);
    boolean isAppointmentUpdateValid(Appointment appointment);
    List<AppointmentResponseDTO> findAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date);
}
