package com.unimag.medicaloffice.mapper;

import com.unimag.medicaloffice.dto.request.ConsultRoomRequestDTO;
import com.unimag.medicaloffice.dto.response.ConsultRoomResponseDTO;
import com.unimag.medicaloffice.model.ConsultRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultRoomMapper {

    ConsultRoom toEntity(ConsultRoomRequestDTO consultRoomRequestDTO);
    ConsultRoomResponseDTO toDTO(ConsultRoom consultRoom);
}
