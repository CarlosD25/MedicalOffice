package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.dto.request.DoctorRequestDTO;
import com.unimag.medicaloffice.dto.response.DoctorResponseDTO;
import com.unimag.medicaloffice.exception.ResourceNotFoundException;
import com.unimag.medicaloffice.mapper.DoctorMapper;
import com.unimag.medicaloffice.model.Doctor;
import com.unimag.medicaloffice.model.Rol;
import com.unimag.medicaloffice.repository.DoctorRepository;
import com.unimag.medicaloffice.service.DoctorService;
import com.unimag.medicaloffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserService userService;

    @Override
    @Transactional
    public DoctorResponseDTO create(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);

        doctor.setUser(userService.createUser(
            doctorRequestDTO.getEmail(),
            doctorRequestDTO.getPassword(),
            Rol.ROLE_DOCTOR
        ));

        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Override
    public DoctorResponseDTO get(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Doctor with id "+id+" not found"));
    }

    @Override
    public List<DoctorResponseDTO> getAll() {
        return doctorRepository.findAll().stream().map(doctorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DoctorResponseDTO update(Long id, DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor with id "+id+" not found"));
        
        doctor.setFullName(doctorRequestDTO.getFullName());
        doctor.setEmail(doctorRequestDTO.getEmail());
        doctor.setSpecialty(doctorRequestDTO.getSpecialty());
        doctor.setAvailableFrom(doctorRequestDTO.getAvailableFrom());
        doctor.setAvailableTo(doctorRequestDTO.getAvailableTo());
        
        if (doctor.getUser() != null) {
            doctor.setUser(userService.updateUser(
                doctor.getUser(),
                doctorRequestDTO.getEmail(),
                doctorRequestDTO.getPassword()
            ));
        }
        
        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Override
    public void delete(Long id) {
        if(!doctorRepository.existsById(id)){
            throw new ResourceNotFoundException("Doctor with id "+id+" not found");
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public boolean isDoctorAvailable(Long id,LocalTime starTime, LocalTime endTime) {
        return doctorRepository.isDoctorAvailable(id, starTime, endTime);
    }

    @Override
    public List<DoctorResponseDTO> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyIgnoreCase(specialty.trim()).stream().map(doctorMapper::toDTO).collect(Collectors.toList());
    }
}
