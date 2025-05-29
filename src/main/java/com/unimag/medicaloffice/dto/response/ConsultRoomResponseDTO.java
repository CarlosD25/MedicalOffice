package com.unimag.medicaloffice.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultRoomResponseDTO {
    private Long id;
    private Integer floor;
    private String description;
}
