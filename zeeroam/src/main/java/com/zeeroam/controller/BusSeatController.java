package com.zeeroam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeeroam.dto.BusDTO;
import com.zeeroam.dto.SeatDTO;
import com.zeeroam.dto.SeatPriceDTO;
import com.zeeroam.request.BusRegisterRequest;
import com.zeeroam.service.BusService;
import com.zeeroam.service.SeatPriceService;

@RestController
@RequestMapping("/api")
public class BusSeatController {

    @Autowired
    private BusService busService;

    @Autowired
    private SeatPriceService seatPriceService;
    
//     ✅ Endpoint to register a new bus with its seats for a bus provider
    @PostMapping("/bus/register")
    public ResponseEntity<BusDTO> registerBus(@RequestBody BusRegisterRequest request) {
        BusDTO busDTO = busService.registerBusWithSeats(request);
        return new ResponseEntity<>(busDTO, HttpStatus.CREATED);
    }

    // ✅ Endpoint to get all seats and their status for a bus
    @GetMapping("/bus/{busId}/seats")
    public ResponseEntity<List<SeatDTO>> getBusSeats(@PathVariable Long busId) {
        List<SeatDTO> seats = busService.getSeatsByBusId(busId);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    // ✅ Endpoint to book a seat
    @PutMapping("/bus/{busId}/seats/{seatNumber}/book")
    public ResponseEntity<String> bookSeat(@PathVariable Long busId, @PathVariable String seatNumber) {
        boolean success = busService.bookSeat(busId, seatNumber);
        if (success) {
            return ResponseEntity.ok("Seat booked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to book seat");
        }
    }

    // ✅ Endpoint to cancel a booking
    @PutMapping("/bus/{busId}/seats/{seatNumber}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long busId, @PathVariable String seatNumber) {
        boolean success = busService.cancelBooking(busId, seatNumber);
        if (success) {
            return ResponseEntity.ok("Seat booking cancelled");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel booking");
        }
    }
    
    @PutMapping("/ride/{rideId}")
    public ResponseEntity<String> updateSeatPrices(@PathVariable Long rideId,
                                                   @RequestBody List<SeatPriceDTO> seatPrices) {
        seatPriceService.updateSeatPrices(rideId, seatPrices);
        return ResponseEntity.ok("Seat prices updated successfully!");
    }
}
