package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.DoctorRequestDTO;
import com.unimag.medicaloffice.dto.response.DoctorResponseDTO;
import com.unimag.medicaloffice.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {


    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO toDTO(Doctor doctor);
}
