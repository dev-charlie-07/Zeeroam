package com.zeeroam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeroam.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsername(String username);
	Optional<RefreshToken> findByUsername(String username);
}
