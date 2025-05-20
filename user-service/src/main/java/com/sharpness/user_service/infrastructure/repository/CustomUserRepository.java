package com.sharpness.user_service.infrastructure.repository;

import com.sharpness.user_service.domain.user.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByUsername(String username);
    Optional<CustomUser> findByEmail(String email);
    Optional<CustomUser> findByUuid(UUID uuid);
}

