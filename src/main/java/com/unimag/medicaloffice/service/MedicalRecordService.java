package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.dto.request.MedicalRecordRequestDTO;
import com.unimag.medicaloffice.dto.response.MedicalRecordResponseDTO;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecordResponseDTO create(MedicalRecordRequestDTO medicalRecordRequestDTO);
    MedicalRecordResponseDTO get(Long id);
    List<MedicalRecordResponseDTO> getAll();
    void delete(Long id);
    List<MedicalRecordResponseDTO> getByPatientId(Long patientId);
}
