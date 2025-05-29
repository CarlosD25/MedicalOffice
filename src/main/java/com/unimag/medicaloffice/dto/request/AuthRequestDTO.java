package com.unimag.medicaloffice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;

    // getters y setters
}
