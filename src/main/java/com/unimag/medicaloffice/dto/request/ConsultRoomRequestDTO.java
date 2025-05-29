package com.unimag.medicaloffice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultRoomRequestDTO {

    @NotBlank
    private String name;

    @Positive
    @NotNull
    private Integer floor;

    private String description;
}
