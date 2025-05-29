package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @BeforeEach
    void setUp() {
        Doctor doctor1 = Doctor.builder()
                .fullName("Camilo Sarmiento")
                .email("cami@hotmail.com")
                .specialty("Cardiólogo")
                .availableFrom(LocalTime.of(8, 0))
                .availableTo(LocalTime.of(16, 0))
                .build();

        Doctor doctor2 = Doctor.builder()
                .fullName("Valentina Díaz")
                .email("valen.diaz@gmail.com")
                .specialty("Dermatóloga")
                .availableFrom(LocalTime.of(9, 0))
                .availableTo(LocalTime.of(17, 0))
                .build();

        Doctor doctor3 = Doctor.builder()
                .fullName("Pedro Suárez")
                .email("pedrosu@gmail.com")
                .specialty("Pediatra")
                .availableFrom(LocalTime.of(7, 30))
                .availableTo(LocalTime.of(15, 30))
                .build();

        Doctor doctor4 = Doctor.builder()
                .fullName("Laura Herrera")
                .email("laura.h@gmail.com")
                .specialty("Ginecóloga")
                .availableFrom(LocalTime.of(10, 0))
                .availableTo(LocalTime.of(18, 0))
                .build();

        Doctor doctor5 = Doctor.builder()
                .fullName("Santiago López")
                .email("santi.lopez@gmail.com")
                .specialty("Neurólogo")
                .availableFrom(LocalTime.of(8, 0))
                .availableTo(LocalTime.of(14, 0))
                .build();

        Doctor doctor6 = Doctor.builder()
                .fullName("Marcela Gómez")
                .email("marcela.gomez@hotmail.com")
                .specialty("Oftalmóloga")
                .availableFrom(LocalTime.of(9, 0))
                .availableTo(LocalTime.of(17, 0))
                .build();

        Doctor doctor7 = Doctor.builder()
                .fullName("Andrés Ramírez")
                .email("andres.ramirez@gmail.com")
                .specialty("Traumatólogo")
                .availableFrom(LocalTime.of(7, 0))
                .availableTo(LocalTime.of(15, 0))
                .build();

        Doctor doctor8 = Doctor.builder()
                .fullName("Carolina Vélez")
                .email("caro.velez@hotmail.com")
                .specialty("Psicóloga")
                .availableFrom(LocalTime.of(11, 0))
                .availableTo(LocalTime.of(19, 0))
                .build();

        Doctor doctor9 = Doctor.builder()
                .fullName("José Moreno")
                .email("jose.moreno@gmail.com")
                .specialty("Internista")
                .availableFrom(LocalTime.of(6, 30))
                .availableTo(LocalTime.of(14, 30))
                .build();

        Doctor doctor10 = Doctor.builder()
                .fullName("Natalia Torres")
                .email("natalia.torres@hotmail.com")
                .specialty("Otorrinolaringóloga")
                .availableFrom(LocalTime.of(12, 0))
                .availableTo(LocalTime.of(20, 0))
                .build();

        doctorRepository.saveAll(List.of(
                doctor1, doctor2, doctor3, doctor4, doctor5,
                doctor6, doctor7, doctor8, doctor9, doctor10
        ));
    }

    @AfterEach
    void tearDown() {
        doctorRepository.deleteAll();
    }

    @Test
    void shouldGetById(){
        Optional<Doctor> doctor = doctorRepository.findById(1L);
        assertTrue(doctor.isPresent());
        assertEquals(doctor.get().getId(),1L);
        assertEquals(doctor.get().getFullName(), "Camilo Sarmiento");
        assertEquals(doctor.get().getEmail(), "cami@hotmail.com");
        assertEquals(doctor.get().getSpecialty(),"Cardiólogo");
        assertEquals(doctor.get().getAvailableFrom(),LocalTime.of(8, 0));
        assertEquals(doctor.get().getAvailableTo(),LocalTime.of(16, 0));
    }

    @Test
    void shouldntGetById(){
        Optional<Doctor> doctor = doctorRepository.findById(200L);
        assertFalse(doctor.isPresent());
    }

    @Test
    void shouldGetAll(){
        List<Doctor> doctors = doctorRepository.findAll();
        assertTrue(doctors.size()==10);
    }

    @Test
    void shouldUpdate(){
        Doctor doctor = doctorRepository.findById(1L).get();
        doctor.setFullName("Carlos Daniel");
        doctor.setEmail("carlos.daniel@gmail.com");
        doctor.setSpecialty("Internista");
        doctor.setAvailableFrom(LocalTime.of(8, 0));
        doctor.setAvailableTo(LocalTime.of(14, 0));
        doctorRepository.save(doctor);
        Doctor updated = doctorRepository.findById(1L).get();
        assertEquals(updated.getId(),1L);
        assertEquals(updated.getFullName(),"Carlos Daniel");
        assertEquals(updated.getEmail(),"carlos.daniel@gmail.com");
        assertEquals(updated.getSpecialty(),"Internista");
        assertEquals(updated.getAvailableFrom(),LocalTime.of(8, 0));
        assertEquals(updated.getAvailableTo(),LocalTime.of(14, 0));
    }

    @Test
    void shouldDelete(){
        doctorRepository.deleteById(1L);
        Optional<Doctor> doctor = doctorRepository.findById(1L);
        assertFalse(doctor.isPresent());
    }
}