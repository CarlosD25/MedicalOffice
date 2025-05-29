package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.dto.request.PatientRequestDTO;
import com.unimag.medicaloffice.dto.response.PatientResponseDTO;
import com.unimag.medicaloffice.exception.ResourceNotFoundException;
import com.unimag.medicaloffice.mapper.PatientMapper;
import com.unimag.medicaloffice.model.Patient;
import com.unimag.medicaloffice.repository.PatientRepository;
import com.unimag.medicaloffice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDTO create(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientMapper.toEntity(patientRequestDTO);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDTO get(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id "+id+" not found"));
    }

    @Override
    public List<PatientResponseDTO> getAll() {
        return patientRepository.findAll().stream().map(patientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PatientResponseDTO update(Long id,PatientRequestDTO patientRequestDTO) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient with id "+id+" not found"));

        patient.setFullName(patientRequestDTO.getFullName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setPhone(patientRequestDTO.getPhone());
        return patientMapper.toDto(patientRepository.save(patient));

    }

    @Override
    public void delete(Long id) {
        if(!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient with id "+id+" not found");
        }
        patientRepository.deleteById(id);
    }


}
