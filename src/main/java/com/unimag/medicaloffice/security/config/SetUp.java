package com.unimag.medicaloffice.security.config;

import com.unimag.medicaloffice.model.Rol;
import com.unimag.medicaloffice.model.Role;
import com.unimag.medicaloffice.model.User;
import com.unimag.medicaloffice.repository.RoleRepository;
import com.unimag.medicaloffice.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
@Transactional
public class SetUp implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository rolesRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SetUp(RoleRepository rolesRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    void createRoleIfNotExist(Rol rol) {
        Optional<Role> roles = rolesRepository.findByRol(rol);

        if(roles.isEmpty()) {
            Role tempRole = new Role();
            tempRole.setRol(rol);
            rolesRepository.save(tempRole);
        }
        System.out.println("Role found: " + rol.toString());
    }

    @Transactional
    public void createAdmin(){
        User user = new User();
        if(!userRepository.existsByEmail("admin@admin.com")){
            Optional<Role> roles = rolesRepository.findByRol(Rol.ROLE_ADMIN);
            if(roles.isPresent()){
                Set<Role> roleSet = new HashSet<>();
                roleSet.add(roles.get());
                user.setRoles(roleSet);
                user.setEmail("admin@admin.com");
                user.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);
            }
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRoleIfNotExist(Rol.ROLE_ADMIN);
        createRoleIfNotExist(Rol.ROLE_DOCTOR);
        createAdmin();
    }
}