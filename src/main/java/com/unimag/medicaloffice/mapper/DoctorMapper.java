package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.DoctorRequestDTO;
import com.unimag.medicaloffice.dto.response.DoctorResponseDTO;
import com.unimag.medicaloffice.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Doctor toEntity(DoctorRequestDTO dto);

    DoctorResponseDTO toDTO(Doctor doctor);
}
