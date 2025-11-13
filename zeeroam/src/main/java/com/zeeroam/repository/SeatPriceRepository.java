package com.zeeroam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeroam.entity.Ride;
import com.zeeroam.entity.Seat;
import com.zeeroam.entity.SeatPrice;

public interface SeatPriceRepository extends JpaRepository<SeatPrice, Long> {

	Optional<SeatPrice> findBySeatAndRide(Seat seat, Ride ride);

}
