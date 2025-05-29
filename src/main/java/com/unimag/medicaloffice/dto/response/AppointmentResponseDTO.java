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
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private Long consultRoomId;
    private String consultRoomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
}
