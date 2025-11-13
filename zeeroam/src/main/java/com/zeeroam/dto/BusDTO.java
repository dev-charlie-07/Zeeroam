package com.zeeroam.dto;

import java.time.LocalDate;
import java.util.List;

import com.zeeroam.util.BusCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {
    private Long id;
    private String busNumber;
    private String busType;
    private BusCategory busCategory; // Fully Seater, Semi-Sleeper, Sleeper
    private int floors; // Number of floors in the bus
    private int totalSeats; // Total seat count
    private boolean hasWifi;
    private boolean hasChargingPoints;
    private boolean hasRecliningSeats;
    private boolean hasMealService;
    private boolean hasBlanket; // Blanket availability
    private boolean hasPillow; // Pillow availability
    private LocalDate lastSanitizedDate; // Last sanitized date
    private double extraLuggageCharge; // Extra charge for luggage
    private Long busProviderId;
    private List<SeatDTO> seats; // Include seat details
}
