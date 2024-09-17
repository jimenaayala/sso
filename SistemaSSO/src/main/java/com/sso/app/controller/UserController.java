package com.sso.app.controller;

import com.sso.app.controller.dto.CreateUserDTO;
import com.sso.app.controller.dto.LoginDTO;
import com.sso.app.entity.securityentity.RoleEntity;
import com.sso.app.entity.securityentity.RoleEnum;
import com.sso.app.entity.securityentity.UserEntity;
import com.sso.app.repository.securityRepository.RoleRepository;
import com.sso.app.repository.securityRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Crea un token de autenticación con el nombre de usuario y contraseña proporcionados
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            // Autentica al usuario
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Establece el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Aquí puedes devolver algún tipo de respuesta de éxito o información adicional
            return ResponseEntity.ok("Login successful");

        } catch (BadCredentialsException e) {
            System.out.println("Error: " + HttpStatus.UNAUTHORIZED.value() + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
