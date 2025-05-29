package com.unimag.medicaloffice.controller;

import com.unimag.medicaloffice.dto.request.DoctorRequestDTO;
import com.unimag.medicaloffice.dto.response.DoctorResponseDTO;
import com.unimag.medicaloffice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.get(id));
    }

    @GetMapping(params = "specialty")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsBySpecialty(@RequestParam String specialty) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialty(specialty));
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> create(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.create(doctorRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> update(@PathVariable Long id, @RequestBody DoctorRequestDTO doctorRequestDTO) {
        return ResponseEntity.ok(doctorService.update(id,doctorRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
