package com.zeeroam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeroam.entity.UserState;

public interface UserStateRepository extends JpaRepository<UserState, Long> {
    Optional<UserState> findByUserId(Long userId);
}
