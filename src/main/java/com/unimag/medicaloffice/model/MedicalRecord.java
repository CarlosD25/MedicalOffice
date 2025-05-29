package com.unimag.medicaloffice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicalrecords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "appointmentId",referencedColumnName = "id")
    private Appointment appointment;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId",referencedColumnName = "id")
    private Patient patient;

    @NotBlank
    private String diagnosis;

    @NotBlank
    private String notes;

    private LocalDateTime createdAt;
}
