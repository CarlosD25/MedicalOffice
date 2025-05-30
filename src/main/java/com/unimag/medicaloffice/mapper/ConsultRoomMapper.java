package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.ConsultRoomRequestDTO;
import com.unimag.medicaloffice.dto.response.ConsultRoomResponseDTO;
import com.unimag.medicaloffice.model.ConsultRoom;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConsultRoomMapper {

    ConsultRoom toEntity(ConsultRoomRequestDTO consultRoomRequestDTO);
    ConsultRoomResponseDTO toDTO(ConsultRoom consultRoom);
}
