package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.PatientRequestDTO;
import com.unimag.medicaloffice.dto.response.PatientResponseDTO;
import com.unimag.medicaloffice.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toEntity(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO toDto(Patient patient);
}
