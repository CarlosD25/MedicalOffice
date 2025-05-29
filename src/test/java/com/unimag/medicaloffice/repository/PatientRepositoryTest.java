package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        Patient patient1 = Patient.builder()
                .fullName("Carlos Sánchez")
                .email("carlos@gmail.com")
                .phone("3024127301")
                .build();

        Patient patient2 = Patient.builder()
                .fullName("Orlando Pérez")
                .email("orlando22@gmail.com")
                .phone("3027747301")
                .build();

        Patient patient3 = Patient.builder()
                .fullName("María Gómez")
                .email("maria.gomez@gmail.com")
                .phone("3012345678")
                .build();

        Patient patient4 = Patient.builder()
                .fullName("Juan Rodríguez")
                .email("juan.rodriguez@gmail.com")
                .phone("3001122334")
                .build();

        Patient patient5 = Patient.builder()
                .fullName("Laura Torres")
                .email("laura.torres@gmail.com")
                .phone("3119988776")
                .build();

        Patient patient6 = Patient.builder()
                .fullName("Andrés Martínez")
                .email("andres.martinez@gmail.com")
                .phone("3105566778")
                .build();

        Patient patient7 = Patient.builder()
                .fullName("Lucía Ramírez")
                .email("lucia.ramirez@gmail.com")
                .phone("3123344556")
                .build();

        Patient patient8 = Patient.builder()
                .fullName("Pedro Jiménez")
                .email("pedro.jimenez@gmail.com")
                .phone("3132233445")
                .build();

        Patient patient9 = Patient.builder()
                .fullName("Sofía Herrera")
                .email("sofia.herrera@gmail.com")
                .phone("3145566778")
                .build();

        Patient patient10 = Patient.builder()
                .fullName("Diego Castro")
                .email("diego.castro@gmail.com")
                .phone("3156677889")
                .build();

        patientRepository.saveAll(List.of(
                patient1, patient2, patient3, patient4, patient5,
                patient6, patient7, patient8, patient9, patient10
        ));


    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    void shouldGetById(){
        Optional<Patient> patient = patientRepository.findById(1L);
        assertTrue(patient.isPresent());
        assertEquals(patient.get().getId(), 1L);
        assertEquals(patient.get().getFullName(),"Carlos Sánchez");
        assertEquals(patient.get().getEmail(), "carlos@gmail.com");
        assertEquals(patient.get().getPhone(), "3024127301");
    }

    @Test
    void shouldntGetById(){
        Optional<Patient> patient = patientRepository.findById(100L);
        assertFalse(patient.isPresent());
    }

    @Test
    void shouldGetAll(){
        List<Patient> patients = patientRepository.findAll();
        assertEquals(patients.size(),10);
    }

    @Test
    void shouldUpdate(){
        Patient patient = patientRepository.findById(1L).get();
        patient.setFullName("Carlos Daniel");
        patient.setEmail("carlos@gmail.edu.co");
        patient.setPhone("3207886924");
        patientRepository.save(patient);
        Patient updated = patientRepository.findById(1L).get();
        assertEquals(updated.getId(),1L);
        assertEquals(updated.getFullName(),"Carlos Daniel");
        assertEquals(updated.getEmail(),"carlos@gmail.edu.co");
        assertEquals(updated.getPhone(),"3207886924");
    }

    @Test
    void shouldDelete(){
        patientRepository.deleteById(1L);
        Optional<Patient> patient = patientRepository.findById(1L);
        assertFalse(patient.isPresent());
    }
}