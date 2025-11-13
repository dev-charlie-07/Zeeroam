package com.zeeroam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeeroam.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findByBusProviderId(Long busProviderId);
    
    Optional<Bus> findByBusNumber(String busNumber);

//	List<Bus> findByRouteId(Long routeId);
}
