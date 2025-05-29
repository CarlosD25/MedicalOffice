package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.dto.request.MedicalRecordRequestDTO;
import com.unimag.medicaloffice.dto.response.MedicalRecordResponseDTO;
import com.unimag.medicaloffice.exception.AppointmentNotCompletedException;
import com.unimag.medicaloffice.mapper.MedicalRecordMapper;
import com.unimag.medicaloffice.model.Appointment;
import com.unimag.medicaloffice.model.MedicalRecord;
import com.unimag.medicaloffice.model.Patient;
import com.unimag.medicaloffice.model.Status;
import com.unimag.medicaloffice.repository.AppointmentRepository;
import com.unimag.medicaloffice.repository.MedicalRecordRepository;
import com.unimag.medicaloffice.repository.PatientRepository;
import com.unimag.medicaloffice.service.MedicalRecordService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    @Override
    public MedicalRecordResponseDTO create(MedicalRecordRequestDTO medicalRecordRequestDTO) {

        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordRequestDTO);

        Appointment appointment = appointmentRepository.findById(medicalRecordRequestDTO.getAppointmentId())
                .orElseThrow(()->new EntityNotFoundException("Appointment not found"));
        Patient patient = patientRepository.findById(medicalRecordRequestDTO.getPatientId())
                .orElseThrow(()->new EntityNotFoundException("Patient not found"));
        if(appointment.getStatus()!=Status.COMPLETED){
            throw new AppointmentNotCompletedException("Appointment not completed");
        }

        medicalRecord.setPatient(patient);
        medicalRecord.setAppointment(appointment);
        return medicalRecordMapper.toDTO(medicalRecordRepository.save(medicalRecord));

    }

    @Override
    public MedicalRecordResponseDTO get(Long id) {
        return medicalRecordMapper.toDTO(medicalRecordRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("MedicalRecord not found")));
    }

    @Override
    public List<MedicalRecordResponseDTO> getAll() {
        return medicalRecordRepository.findAll().stream().map(medicalRecordMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(!medicalRecordRepository.existsById(id)){
            throw new EntityNotFoundException("MedicalRecord not found");
        }
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public List<MedicalRecordResponseDTO> getByPatientId(Long patientId) {
        return medicalRecordRepository.findById(patientId).stream().map(medicalRecordMapper::toDTO).collect(Collectors.toList());
    }
}
