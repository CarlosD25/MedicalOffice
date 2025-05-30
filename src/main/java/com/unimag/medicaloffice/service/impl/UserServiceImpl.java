package com.unimag.medicaloffice.service.impl;

import com.unimag.medicaloffice.exception.ResourceNotFoundException;
import com.unimag.medicaloffice.model.Rol;
import com.unimag.medicaloffice.model.Role;
import com.unimag.medicaloffice.model.User;
import com.unimag.medicaloffice.repository.RoleRepository;
import com.unimag.medicaloffice.repository.UserRepository;
import com.unimag.medicaloffice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(String email, String password, Rol rol) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Optional<Role> roleOpt = roleRepository.findByRol(rol);
        if (roleOpt.isEmpty()) {
            throw new ResourceNotFoundException("El rol " + rol + " no existe");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleOpt.get());

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, String email, String password) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        if (email != null && !email.equals(user.getEmail())) {
            if (userRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("El email ya está registrado");
            }
            user.setEmail(email);
        }

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        return userRepository.save(user);
    }
} 