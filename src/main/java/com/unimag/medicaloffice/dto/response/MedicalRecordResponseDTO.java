package com.unimag.medicaloffice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordResponseDTO {

    private Long id;
    private Long appointmentId;
    private Long patientId;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
}
