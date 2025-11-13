package com.zeeroam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeeroam.entity.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
