package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.ConsultRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ConsultRoomRepository extends JpaRepository<ConsultRoom, Long> {

}
