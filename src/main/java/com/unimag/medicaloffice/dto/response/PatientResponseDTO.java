package com.unimag.medicaloffice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phone;

}
