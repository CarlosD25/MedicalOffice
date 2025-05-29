package com.unimag.medicaloffice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDTO {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    private String specialty;

    private LocalTime availableFrom;

    private LocalTime availableTo;
}
