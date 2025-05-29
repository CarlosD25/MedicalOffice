package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.AppointmentRequestDTO;
import com.unimag.medicaloffice.dto.response.AppointmentResponseDTO;
import com.unimag.medicaloffice.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor",ignore = true)
    @Mapping(target = "consultRoom",ignore = true)
    Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO);

    @Mapping(target = "patient", source = "patient")
    @Mapping(target = "doctor",source = "doctor")
    @Mapping(target = "consultRoom",source = "consultRoom" )
    AppointmentResponseDTO toDTO(Appointment appointment);
}
