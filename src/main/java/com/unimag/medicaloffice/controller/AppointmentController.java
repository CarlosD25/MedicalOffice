package com.unimag.medicaloffice.controller;

import com.unimag.medicaloffice.dto.request.AppointmentRequestDTO;
import com.unimag.medicaloffice.dto.response.AppointmentResponseDTO;
import com.unimag.medicaloffice.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.get(id));
    }

    @GetMapping(params = {"doctorId", "date"})
    public ResponseEntity<List<AppointmentResponseDTO>> findAppointmentsByDoctorIdAndDate(@RequestParam Long doctorId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(appointmentService.findAppointmentsByDoctorIdAndDate(doctorId,date));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.create(appointmentRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@PathVariable Long id, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return ResponseEntity.ok(appointmentService.update(id, appointmentRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
