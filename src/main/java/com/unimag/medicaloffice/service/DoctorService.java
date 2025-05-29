package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.dto.request.DoctorRequestDTO;
import com.unimag.medicaloffice.dto.response.DoctorResponseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface DoctorService {

    DoctorResponseDTO create(DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO get(Long id);
    List<DoctorResponseDTO> getAll();
    DoctorResponseDTO update(Long id,DoctorRequestDTO doctorRequestDTO);
    void delete(Long id);
    boolean isDoctorAvailable(Long id,LocalTime starTime, LocalTime endTime);
    List<DoctorResponseDTO> getDoctorsBySpecialty(String specialty);
}
