package com.unimag.medicaloffice.controller;

import com.unimag.medicaloffice.dto.request.ConsultRoomRequestDTO;
import com.unimag.medicaloffice.dto.response.ConsultRoomResponseDTO;
import com.unimag.medicaloffice.service.ConsultRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class ConsultRoomController {

    private final ConsultRoomService consultRoomService;

    @GetMapping
    public ResponseEntity<List<ConsultRoomResponseDTO>> getAll(){
        return ResponseEntity.ok(consultRoomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultRoomResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(consultRoomService.get(id));
    }

    @PostMapping
    public ResponseEntity<ConsultRoomResponseDTO> create(@RequestBody ConsultRoomRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultRoomService.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultRoomResponseDTO> update(@PathVariable Long id,@RequestBody ConsultRoomRequestDTO requestDTO) {
        return ResponseEntity.ok(consultRoomService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultRoomResponseDTO> delete(@PathVariable Long id) {
        consultRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
