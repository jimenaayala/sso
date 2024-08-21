package com.sso.app.controller;

import com.sso.app.controller.dto.CreateUserDTO;
import com.sso.app.entity.securityentity.RoleEntity;
import com.sso.app.entity.securityentity.RoleEnum;
import com.sso.app.entity.securityentity.UserEntity;
import com.sso.app.repository.securityRepository.RoleRepository;
import com.sso.app.repository.securityRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    private final RoleRepository roleRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = new HashSet<>();

        for (String roleName : createUserDTO.getRoles()) {
            RoleEnum roleEnum;
            try {
                roleEnum = RoleEnum.valueOf(roleName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Role " + roleName + " does not exist.");
            }

            RoleEntity role = roleRepository.findByRoleEnum(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Role " + roleEnum + " not found."));
            roles.add(role);
        }

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword())) // Encriptar la contraseña
                .roles(roles)
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok("User created successfully.");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("User " + authentication.getName() + " is authenticated.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }
    }
}
