package com.unimag.medicaloffice.repository;

import com.unimag.medicaloffice.model.Rol;
import com.unimag.medicaloffice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRol(Rol rol);
}
