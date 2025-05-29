package com.unimag.medicaloffice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordRequestDTO {

    private Long appointmentId;

    private Long patientId;

    @NotBlank
    private String diagnosis;

    @NotBlank
    private String notes;

    private LocalDateTime createdAt;
}
