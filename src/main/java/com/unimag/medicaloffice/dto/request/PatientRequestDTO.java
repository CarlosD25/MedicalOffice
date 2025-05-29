package com.unimag.medicaloffice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequestDTO {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    private String phone;
}
