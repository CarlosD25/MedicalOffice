package com.unimag.medicaloffice.service;

import com.unimag.medicaloffice.model.Rol;
import com.unimag.medicaloffice.model.User;

public interface UserService {
    User createUser(String email, String password, Rol rol);
    User updateUser(User user, String email, String password);
}
