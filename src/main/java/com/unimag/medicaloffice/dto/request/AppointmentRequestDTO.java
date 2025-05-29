package com.unimag.medicaloffice.dto.request;

import com.unimag.medicaloffice.model.Status;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {

    private Long patientId;
    private Long doctorId;
    private Long consultRoomId;

    @Future
    private LocalDateTime startTime;

    @Future
    private LocalDateTime endTime;

    private Status status;
}
