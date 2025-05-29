package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.dto.request.PatientRequestDTO;
import com.unimag.medicaloffice.dto.response.PatientResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientService {

    PatientResponseDTO create(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO get(Long id);
    List<PatientResponseDTO> getAll();
    PatientResponseDTO update(Long id,PatientRequestDTO patientRequestDTO);
    void delete(Long id);
}
