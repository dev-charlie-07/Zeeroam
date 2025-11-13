package com.zeeroam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeeroam.entity.BusProvider;

@Repository
public interface BusProviderRepository extends JpaRepository<BusProvider, Long> {
    Optional<BusProvider> findByEmail(String email);  // To check if the email is already registered
}
