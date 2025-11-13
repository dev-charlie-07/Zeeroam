package com.zeeroam.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeroam.entity.Bus;
import com.zeeroam.entity.BusRouteAssignment;
import com.zeeroam.entity.Route;

public interface BusRouteAssignmentRepository extends JpaRepository<BusRouteAssignment, Long> {
    
    // Find existing assignment for the bus, route, and date
    Optional<BusRouteAssignment> findByBusAndRouteAndAssignmentDate(Bus bus, Route route, LocalDateTime assignmentDate);
    
    List<BusRouteAssignment> findByRouteId(Long routeId);
    
    Optional<BusRouteAssignment> findByBusIdAndRouteIdAndAssignmentDate(Long busId, Long routeId, LocalDateTime departureTime);
}
