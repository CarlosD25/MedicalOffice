package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.dto.request.AppointmentRequestDTO;
import com.unimag.medicaloffice.dto.response.AppointmentResponseDTO;
import com.unimag.medicaloffice.exception.ScheduleConflictException;
import com.unimag.medicaloffice.mapper.AppointmentMapper;
import com.unimag.medicaloffice.model.Appointment;
import com.unimag.medicaloffice.model.ConsultRoom;
import com.unimag.medicaloffice.model.Doctor;
import com.unimag.medicaloffice.model.Patient;
import com.unimag.medicaloffice.repository.AppointmentRepository;
import com.unimag.medicaloffice.repository.ConsultRoomRepository;
import com.unimag.medicaloffice.repository.DoctorRepository;
import com.unimag.medicaloffice.repository.PatientRepository;
import com.unimag.medicaloffice.service.AppointmentService;
import com.unimag.medicaloffice.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorService doctorService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ConsultRoomRepository consultRoomRepository;

    @Override
    public AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO) {

        Patient patient = patientRepository.findById(appointmentRequestDTO.getPatientId())
                .orElseThrow(()-> new EntityNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(appointmentRequestDTO.getDoctorId())
                .orElseThrow(()-> new EntityNotFoundException("Doctor not found"));
        ConsultRoom consultRoom = consultRoomRepository.findById(appointmentRequestDTO.getConsultRoomId())
                .orElseThrow(()-> new EntityNotFoundException("ConsultRoom not found"));

        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setConsultRoom(consultRoom);

        if(!isAppointmentValid(appointment)){
            throw new ScheduleConflictException("The appointment is not available.");
         }
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentResponseDTO get(Long id) {
        return appointmentRepository.findById(id).map(appointmentMapper::toDTO)
                .orElseThrow(()->new EntityNotFoundException("No appointment found with id " + id));
    }

    @Override
    public List<AppointmentResponseDTO> getAll() {
        return appointmentRepository.findAll().stream().map(appointmentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO appointmentRequestDTO) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("No appointment found with id " + id));

        ConsultRoom consultRoom = consultRoomRepository
                .findById(appointmentRequestDTO.getConsultRoomId())
                .orElseThrow(()->new EntityNotFoundException("ConsultRoom not found"));

        LocalDateTime startTime = appointmentRequestDTO.getStartTime();
        LocalDateTime endTime = appointmentRequestDTO.getEndTime();

        if(!isAppointmentUpdateValid(appointment)){
            throw new ScheduleConflictException("The appointment is not available.");
        }

        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);
        appointment.setConsultRoom(consultRoom);
        appointment.setStatus(appointmentRequestDTO.getStatus());
        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public void delete(Long id) {
        if(!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("No appointment found with id " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public boolean isAppointmentValid(Appointment appointment) {

        Long doctorId = appointment.getDoctor().getId();
        Long patientId = appointment.getPatient().getId();
        Long consultRoomId = appointment.getConsultRoom().getId();
        LocalTime startTimeH = appointment.getStartTime().toLocalTime();
        LocalTime endTimeH = appointment.getEndTime().toLocalTime();

        if(!doctorService.isDoctorAvailable(doctorId,startTimeH,endTimeH)){ return false;}
        if(appointmentRepository.existsConflictInConsultRoom(consultRoomId,appointment.getStartTime(),appointment.getEndTime())){ return false;}
        if(appointmentRepository.existsConflictInDoctorSchedule(doctorId,appointment.getStartTime(),appointment.getEndTime())){ return false;}
        if(appointmentRepository.existsConflictInPatientSchedule(patientId,appointment.getStartTime(),appointment.getEndTime())){ return false;}

        return true;
    }

    @Override
    public boolean isAppointmentUpdateValid(Appointment appointment) {
        Long doctorId = appointment.getDoctor().getId();
        Long patientId = appointment.getPatient().getId();
        Long consultRoomId = appointment.getConsultRoom().getId();
        LocalTime startTimeH = appointment.getStartTime().toLocalTime();
        LocalTime endTimeH = appointment.getEndTime().toLocalTime();

        if(appointmentRepository
                .existsDoctorConflictExcludingAppointment
                (doctorId,appointment.getStartTime(),
                appointment.getEndTime(),appointment.getId())){return false;}

        if(appointmentRepository
                .existsPatientConflictExcludingAppointment
                (patientId,appointment.getStartTime(),
                appointment.getEndTime(),appointment.getId())){return false;}

        if(appointmentRepository
                .existsConsultRoomConflictExcludingAppointment
                (consultRoomId,appointment.getStartTime(),
                appointment.getEndTime(),appointment.getId())){return false;}

        if(!doctorService.isDoctorAvailable(doctorId,startTimeH,endTimeH)){ return false;}

        return true;
    }

    @Override
    public List<AppointmentResponseDTO> findAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date) {

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new EntityNotFoundException("Doctor not found"));
        LocalDate date1 = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        LocalDateTime startOfDay = date1.atStartOfDay();
        LocalDateTime endOfDay = date1.atTime(LocalTime.MAX);

        return appointmentRepository.findAppointmentsByDoctorIdAndDate(doctorId,startOfDay,endOfDay)
                .stream().map(appointmentMapper::toDTO).collect(Collectors.toList());

    }


}
