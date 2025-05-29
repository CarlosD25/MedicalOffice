package com.unimag.medicaloffice.controller;

import com.unimag.medicaloffice.dto.request.MedicalRecordRequestDTO;
import com.unimag.medicaloffice.dto.response.MedicalRecordResponseDTO;
import com.unimag.medicaloffice.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDTO>> getAll() {
        return ResponseEntity.ok(medicalRecordService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.get(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> create(@RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.create(medicalRecordRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> delete(@PathVariable Long id) {
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
