package com.unimag.medicaloffice.dto.response;

import com.unimag.medicaloffice.model.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {
    private Long id;
    private PatientResponseDTO patient;
    private DoctorResponseDTO doctor;
    private ConsultRoomResponseDTO consultRoom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
}
