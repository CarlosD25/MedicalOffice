package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.MedicalRecordRequestDTO;
import com.unimag.medicaloffice.dto.response.MedicalRecordResponseDTO;
import com.unimag.medicaloffice.model.MedicalRecord;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicalRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    MedicalRecord toEntity(MedicalRecordRequestDTO dto);

    @Mapping(target = "appointmentId", source = "appointment.id")
    @Mapping(target = "patientId", source = "patient.id")
    MedicalRecordResponseDTO toDTO(MedicalRecord entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget MedicalRecord entity, MedicalRecordRequestDTO dto);
}
