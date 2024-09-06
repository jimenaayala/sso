package com.sso.app.config;

import com.sso.app.service.servicesecurity.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si no usas formularios
                .httpBasic(Customizer.withDefaults()) // Autenticación básica
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API sin estado
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll() // Permitir el login sin autenticación previa
                        // .requestMatchers(HttpMethod.POST, "/users/create").hasAnyRole("ADMIN") // Restringir este endpoint a "ADMIN"
                        .anyRequest().authenticated() // El resto de endpoints requieren autenticación
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); // Origen permitido (cambiar por el origen real)
        config.addAllowedHeader("*"); // Permitir todos los headers
        config.addAllowedMethod("*"); // Permitir todos los métodos HTTP
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // Establecer codificador de contraseñas
        provider.setUserDetailsService(userDetailService); // Establecer el servicio de detalles de usuario
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Usar BCrypt para codificar contraseñas
    }
}
