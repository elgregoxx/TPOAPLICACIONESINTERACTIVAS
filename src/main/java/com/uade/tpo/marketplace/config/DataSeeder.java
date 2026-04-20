package com.uade.tpo.marketplace.config;

import com.uade.tpo.marketplace.model.Role;
import com.uade.tpo.marketplace.model.User;
import com.uade.tpo.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@marketplace.com")) {
                userRepository.save(User.builder()
                        .name("Administrador")
                        .email("admin@marketplace.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .build());
            }
            if (!userRepository.existsByEmail("juan@marketplace.com")) {
                userRepository.save(User.builder()
                        .name("Juan Profesor")
                        .email("juan@marketplace.com")
                        .password(passwordEncoder.encode("teacher123"))
                        .role(Role.ROLE_TEACHER)
                        .build());
            }
            if (!userRepository.existsByEmail("ana@marketplace.com")) {
                userRepository.save(User.builder()
                        .name("Ana Alumna")
                        .email("ana@marketplace.com")
                        .password(passwordEncoder.encode("student123"))
                        .role(Role.ROLE_STUDENT)
                        .build());
            }
        };
    }
}
