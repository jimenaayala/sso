package com.sso.app.controller;

import com.sso.app.controller.dto.CreateUserDTO;
import com.sso.app.controller.dto.LoginRequest;
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
@RequestMapping("api")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;


    private final RoleRepository roleRepository;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = new HashSet<>();

        for (String roleName : createUserDTO.getRoles()) {
            RoleEnum roleEnum;
            try {
                roleEnum = RoleEnum.valueOf(roleName.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity
                        .badRequest()
                        .body("Role " + roleName + " does not exist.");
            }

            RoleEntity role = roleRepository.findByRoleEnum(roleEnum).orElseGet(() -> {
                // Si el rol no existe, lo creamos y guardamos en la base de datos
                RoleEntity newRole = RoleEntity.builder()
                        .roleEnum(roleEnum)
                        .build();
                return roleRepository.save(newRole);
            });

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
    return ResponseEntity.ok("User created successfully.");}



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("entro");
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        // Aquí deberías realizar la autenticación utilizando Spring Security o manualmente
        if (autenticarUsuario(loginRequest)) {
            System.out.println("estas logueado");
            return ResponseEntity.ok("Login exitoso");
        } else {
            System.out.println("estas rebotado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login fallido");
        }
    }

    private boolean autenticarUsuario(LoginRequest loginRequest) {

        // Lógica de autenticación (por ejemplo, verificar el usuario y contraseña en la base de datos)
        return true; // Cambia esto según tu lógica
    }

}
