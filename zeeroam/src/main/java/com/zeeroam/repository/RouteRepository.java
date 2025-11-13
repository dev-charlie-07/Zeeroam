package com.zeeroam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeeroam.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}

