package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.dto.request.ConsultRoomRequestDTO;
import com.unimag.medicaloffice.dto.response.ConsultRoomResponseDTO;
import java.util.List;

public interface ConsultRoomService {

    ConsultRoomResponseDTO create(ConsultRoomRequestDTO consultRoomRequestDTO);
    ConsultRoomResponseDTO get(Long id);
    List<ConsultRoomResponseDTO> getAll();
    ConsultRoomResponseDTO update(Long id,ConsultRoomRequestDTO consultRoomRequestDTO);
    void delete(Long id);
}
