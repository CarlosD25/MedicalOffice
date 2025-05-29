package com.unimag.medicaloffice.controller;

import com.unimag.medicaloffice.dto.request.PatientRequestDTO;
import com.unimag.medicaloffice.dto.response.PatientResponseDTO;
import com.unimag.medicaloffice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAll() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.get(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.create(patientRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable Long id,@RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.update(id,patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
