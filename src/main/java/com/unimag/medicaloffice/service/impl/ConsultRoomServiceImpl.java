package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.dto.request.ConsultRoomRequestDTO;
import com.unimag.medicaloffice.dto.response.ConsultRoomResponseDTO;
import com.unimag.medicaloffice.exception.ResourceNotFoundException;
import com.unimag.medicaloffice.mapper.ConsultRoomMapper;
import com.unimag.medicaloffice.model.ConsultRoom;
import com.unimag.medicaloffice.repository.ConsultRoomRepository;
import com.unimag.medicaloffice.service.ConsultRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultRoomServiceImpl implements ConsultRoomService {

    private final ConsultRoomRepository consultRoomRepository;
    private final ConsultRoomMapper consultRoomMapper;

    @Override
    public ConsultRoomResponseDTO create(ConsultRoomRequestDTO consultRoomRequestDTO) {
        ConsultRoom consultRoom = consultRoomMapper.toEntity(consultRoomRequestDTO);
        return consultRoomMapper.toDTO(consultRoomRepository.save(consultRoom));
    }

    @Override
    public ConsultRoomResponseDTO get(Long id) {
        return consultRoomRepository.findById(id).map(consultRoomMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("ConsultRoom with id "+id+" not found"));
    }

    @Override
    public List<ConsultRoomResponseDTO> getAll() {
        return consultRoomRepository.findAll().stream().map(consultRoomMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ConsultRoomResponseDTO update(Long id,ConsultRoomRequestDTO consultRoomRequestDTO) {
        ConsultRoom consultRoom = consultRoomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ConsultRoom with id "+id+" not found"));
        consultRoom.setName(consultRoomRequestDTO.getName());
        consultRoom.setFloor(consultRoomRequestDTO.getFloor());
        consultRoom.setDescription(consultRoomRequestDTO.getDescription());
        return consultRoomMapper.toDTO(consultRoomRepository.save(consultRoom));
    }

    @Override
    public void delete(Long id) {
        if(!consultRoomRepository.existsById(id)) {
            throw new ResourceNotFoundException("ConsultRoom with id "+id+" not found");
        }
        consultRoomRepository.deleteById(id);
    }


}
