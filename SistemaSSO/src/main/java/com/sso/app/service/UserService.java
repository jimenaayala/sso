package com.sso.app.service;

import com.sso.app.entity.security.User;
import com.sso.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;

    public User registerNewUser(String email, String password, String role) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception("Usuario no encontrado");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(BCryptPasswordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
}