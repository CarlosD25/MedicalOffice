package com.unimag.medicaloffice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId",referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "consultRoomId",referencedColumnName = "id")
    private ConsultRoom consultRoom;

    @Future
    private LocalDateTime startTime;

    @Future
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private Status status= Status.SCHEDULED;

}
