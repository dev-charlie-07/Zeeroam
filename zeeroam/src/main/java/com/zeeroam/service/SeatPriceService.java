package com.zeeroam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeroam.dto.SeatPriceDTO;
import com.zeeroam.entity.Ride;
import com.zeeroam.entity.Seat;
import com.zeeroam.entity.SeatPrice;
import com.zeeroam.exception.CustomException;
import com.zeeroam.exception.ErrorCode;
import com.zeeroam.repository.RideRepository;
import com.zeeroam.repository.SeatPriceRepository;
import com.zeeroam.repository.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatPriceService {

    @Autowired
    private SeatPriceRepository seatPriceRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RideRepository rideRepository;

    @Transactional
    public void updateSeatPrices(Long rideId, List<SeatPriceDTO> seatPriceDTOs) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new CustomException(ErrorCode.RIDE_NOT_FOUND)); // Fetch ride

        List<SeatPrice> seatPrices = seatPriceDTOs.stream().map(dto -> {
            Seat seat = seatRepository.findById(dto.getSeatId())
                    .orElseThrow(() -> new CustomException(ErrorCode.SEAT_NOT_FOUND)); // Fetch seat

            Optional<SeatPrice> seatPrice_OPT = seatPriceRepository.findBySeatAndRide(seat, ride);

            SeatPrice seatPrice;
            if (seatPrice_OPT.isEmpty()) {
                seatPrice = new SeatPrice(); // ✅ Fix: Initialize seatPrice before using it
                seatPrice.setSeat(seat);
                seatPrice.setRide(ride);
                seatPrice.setPrice(dto.getPrice());
            } else {
                seatPrice = seatPrice_OPT.get();
                seatPrice.setPrice(dto.getPrice()); // ✅ Update price for existing seatPrice
            }

            return seatPrice;
        }).collect(Collectors.toList());

        seatPriceRepository.saveAll(seatPrices);
    }
}
