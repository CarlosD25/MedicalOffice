package com.unimag.medicaloffice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }
}