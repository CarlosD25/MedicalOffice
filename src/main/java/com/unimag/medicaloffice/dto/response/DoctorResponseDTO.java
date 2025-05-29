package com.unimag.medicaloffice.dto.response;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String specialty;
    private LocalTime availableFrom;
    private LocalTime availableTo;
}
