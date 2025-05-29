package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.AppointmentRequestDTO;
import com.unimag.medicaloffice.dto.response.AppointmentResponseDTO;
import com.unimag.medicaloffice.model.Appointment;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "consultRoom", ignore = true)
    Appointment toEntity(AppointmentRequestDTO dto);

    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName", source = "patient.fullName")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "doctorName", source = "doctor.fullName")
    @Mapping(target = "consultRoomId", source = "consultRoom.id")
    @Mapping(target = "consultRoomName", source = "consultRoom.name")
    AppointmentResponseDTO toDTO(Appointment entity);

    void updateEntity(@MappingTarget Appointment entity, AppointmentRequestDTO dto);
}
