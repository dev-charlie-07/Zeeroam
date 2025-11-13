package com.zeeroam.service;

import com.zeeroam.dto.BusDTO;
import com.zeeroam.dto.SeatDTO;
import com.zeeroam.entity.Bus;
import com.zeeroam.entity.BusProvider;
import com.zeeroam.entity.Seat;
import com.zeeroam.exception.CustomException;
import com.zeeroam.exception.ErrorCode;
import com.zeeroam.repository.BusProviderRepository;
import com.zeeroam.repository.BusRepository;
import com.zeeroam.repository.SeatRepository;
import com.zeeroam.request.BusRegisterRequest;
import com.zeeroam.util.SeatStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private BusProviderRepository busProviderRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public BusDTO registerBusWithSeats(BusRegisterRequest request) {

        System.out.println(request);
        
        // Fetch the bus provider
        BusProvider busProvider = busProviderRepository.findById(request.getBusProviderId())
                .orElseThrow(() -> new CustomException(ErrorCode.BUS_PROVIDER_NOT_FOUND));

        // Check if the bus number already exists
        if (busRepository.findByBusNumber(request.getBusNumber()).isPresent()) {
            throw new CustomException(ErrorCode.BUS_NUMBER_ALREADY_EXISTS);
        }

        // Create and save the bus
        Bus bus = new Bus();
        bus.setBusNumber(request.getBusNumber());
        bus.setBusType(request.getBusType());
        bus.setBusCategory(request.getBusCategory());
        bus.setTotalSeats(request.getTotalSeats());
        bus.setFloors(request.getNumberOfFloors());
        bus.setHasWifi(request.isHasWifi());
        bus.setHasChargingPoints(request.isHasChargingPoints());
        bus.setHasRecliningSeats(request.isHasRecliningSeats());
        bus.setHasMealService(request.isHasMealService());
        bus.setHasBlanket(request.isHasBlanket());
        bus.setHasPillow(request.isHasPillow());
        bus.setLastSanitizedDate(request.getLastSanitizedDate());
        bus.setExtraLuggageCharge(request.getExtraLuggageCharge());
        bus.setBusProvider(busProvider);
        
        busRepository.save(bus);

        // Create and save seats for the bus
        List<Seat> seats = request.getSeats().stream()
                .map(seatRequest -> {
                    Seat seat = new Seat();
                    seat.setSeatNumber(seatRequest.getSeatNumber());
                    seat.setStatus(SeatStatus.AVAILABLE);  // Initially, all seats are available
                    seat.setSeatType(seatRequest.getSeatType());
                    seat.setGenderRestriction(seatRequest.getGenderRestriction());
                    seat.setWindowSeat(seatRequest.isWindowSeat());
                    seat.setBus(bus);
                    return seat;
                }).collect(Collectors.toList());

        seatRepository.saveAll(seats);

        return new BusDTO(
            bus.getId(),
            bus.getBusNumber(),
            bus.getBusType(),
            bus.getBusCategory(),
            bus.getFloors(),
            bus.getTotalSeats(),
            bus.isHasWifi(),
            bus.isHasChargingPoints(),
            bus.isHasRecliningSeats(),
            bus.isHasMealService(),
            bus.isHasBlanket(),
            bus.isHasPillow(),
            bus.getLastSanitizedDate(),
            bus.getExtraLuggageCharge(),
            bus.getBusProvider().getId(),
            seats.stream().map(seat -> 
                new SeatDTO(
                    seat.getSeatNumber(), 
                    seat.getStatus(), 
                    seat.getSeatType(), 
                    seat.isWindowSeat(),
                    seat.getGenderRestriction()
                )
            ).collect(Collectors.toList())
        );
    }

    
    // ✅ Get all seats and their status for a bus
    public List<SeatDTO> getSeatsByBusId(Long busId) {
        return seatRepository.findByBusId(busId).stream()
                .map(seat -> new SeatDTO(seat.getSeatNumber(), seat.getStatus(), seat.getSeatType(), seat.isWindowSeat(), seat.getGenderRestriction()))
                .collect(Collectors.toList());
    }

    // ✅ Book a seat
    @Transactional
    public boolean bookSeat(Long busId, String seatNumber) {
        Seat seat = seatRepository.findByBusId(busId).stream()
                .filter(s -> s.getSeatNumber().equals(seatNumber))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.SEAT_NOT_FOUND));

        if (seat.getStatus() == SeatStatus.AVAILABLE) {
            seat.setStatus(SeatStatus.BOOKED);
            seatRepository.save(seat);
            return true;
        }

        throw new CustomException(ErrorCode.SEAT_ALREADY_BOOKED);
    }

    // ✅ Cancel seat booking
    @Transactional
    public boolean cancelBooking(Long busId, String seatNumber) {
        Seat seat = seatRepository.findByBusId(busId).stream()
                .filter(s -> s.getSeatNumber().equals(seatNumber))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.SEAT_NOT_FOUND));

        if (seat.getStatus() == SeatStatus.BOOKED) {
            seat.setStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);
            return true;
        }

        throw new CustomException(ErrorCode.SEAT_NOT_BOOKED);
    }
}
