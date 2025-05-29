package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.MedicalRecordRequestDTO;
import com.unimag.medicaloffice.dto.response.MedicalRecordResponseDTO;
import com.unimag.medicaloffice.model.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    @Mapping(target = "patient",ignore = true)
    @Mapping(target = "appointment",ignore = true)
    MedicalRecord toEntity(MedicalRecordRequestDTO medicalRecordRequestDTO);

    @Mapping(target = "appointmentId",source = "appointment.id")
    @Mapping(target = "patientId",source = "patient.id")
    MedicalRecordResponseDTO toDTO(MedicalRecord medicalRecord);

}
