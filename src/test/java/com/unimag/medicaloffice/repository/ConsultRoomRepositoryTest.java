package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.ConsultRoom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
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
class ConsultRoomRepositoryTest {

    @Autowired
    private ConsultRoomRepository consultRoomRepository;

    @BeforeEach
    void setUp() {

        ConsultRoom consultRoom1 = ConsultRoom.builder()
                .name("Consultorio de muestras de sangre")
                .floor(2)
                .description("Extraer muestras de sangre")
                .build();

        ConsultRoom consultRoom2 = ConsultRoom.builder()
                .name("Consultorio de pediatría")
                .floor(1)
                .description("Atención médica a niños y adolescentes")
                .build();

        ConsultRoom consultRoom3 = ConsultRoom.builder()
                .name("Consultorio de medicina general")
                .floor(1)
                .description("Atención médica general y chequeos")
                .build();

        ConsultRoom consultRoom4 = ConsultRoom.builder()
                .name("Consultorio de cardiología")
                .floor(3)
                .description("Diagnóstico y tratamiento de enfermedades del corazón")
                .build();

        ConsultRoom consultRoom5 = ConsultRoom.builder()
                .name("Consultorio de ginecología")
                .floor(2)
                .description("Salud reproductiva y atención ginecológica")
                .build();

        ConsultRoom consultRoom6 = ConsultRoom.builder()
                .name("Consultorio de dermatología")
                .floor(2)
                .description("Tratamiento de enfermedades de la piel")
                .build();

        ConsultRoom consultRoom7 = ConsultRoom.builder()
                .name("Consultorio de neurología")
                .floor(3)
                .description("Tratamiento de enfermedades neurológicas")
                .build();

        ConsultRoom consultRoom8 = ConsultRoom.builder()
                .name("Consultorio de oftalmología")
                .floor(3)
                .description("Diagnóstico y tratamiento de problemas visuales")
                .build();

        ConsultRoom consultRoom9 = ConsultRoom.builder()
                .name("Consultorio de fisioterapia")
                .floor(1)
                .description("Terapia física y rehabilitación")
                .build();

        ConsultRoom consultRoom10 = ConsultRoom.builder()
                .name("Consultorio de nutrición")
                .floor(1)
                .description("Asesoría y planes de alimentación")
                .build();

        consultRoomRepository.saveAll(List.of(
                consultRoom1, consultRoom2, consultRoom3, consultRoom4, consultRoom5,
                consultRoom6, consultRoom7, consultRoom8, consultRoom9, consultRoom10
        ));

    }

    @AfterEach
    void tearDown() {
        consultRoomRepository.deleteAll();
    }

    @Test
    void shouldGetById(){
        Optional<ConsultRoom> consultRoom = consultRoomRepository.findById(1L);
        assertTrue(consultRoom.isPresent());
        assertEquals(consultRoom.get().getName(), "Consultorio de muestras de sangre");
        assertEquals(consultRoom.get().getFloor(),2);
        assertEquals(consultRoom.get().getDescription(),"Extraer muestras de sangre");
    }

    @Test
    void shouldntGetById(){
        Optional<ConsultRoom> consultRoom = consultRoomRepository.findById(200L);
        assertFalse(consultRoom.isPresent());
    }

    @Test
    void shouldGetAll(){
        List<ConsultRoom> consultRooms = consultRoomRepository.findAll();
        assertTrue(consultRooms.size()==10);
    }

    @Test
    void shouldUpdate(){
        ConsultRoom consultRoom = consultRoomRepository.findById(1L).get();
        consultRoom.setName("Consultorio de pruebas covid");
        consultRoom.setFloor(10);
        consultRoom.setDescription("Prueba y tratamiento de enfermedades");
        consultRoomRepository.save(consultRoom);
        ConsultRoom updated = consultRoomRepository.findById(1L).get();
        assertEquals(updated.getId(),1L);
        assertEquals(updated.getName(), "Consultorio de pruebas covid");
        assertEquals(updated.getFloor(), 10);
        assertEquals(updated.getDescription(), "Prueba y tratamiento de enfermedades");
    }

    @Test
    void shouldDelete(){
        consultRoomRepository.deleteById(1L);
        Optional<ConsultRoom> consultRoom = consultRoomRepository.findById(1L);
        assertFalse(consultRoom.isPresent());
    }
}