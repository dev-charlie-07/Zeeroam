package com.zeeroam.request;

import java.time.LocalDate;
import java.util.List;

import com.zeeroam.util.BusCategory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusRegisterRequest {
	private Long busProviderId;
	private String busNumber;
	private String busType;
	private BusCategory busCategory; // FULL_SEATER, FULL_SLEEPER, SEMI_SLEEPER
	private int totalSeats;
	private int numberOfFloors;
	private boolean hasWifi;
	private boolean hasChargingPoints;
	private boolean hasRecliningSeats;
	private boolean hasMealService;
	private boolean hasBlanket;
	private boolean hasPillow;
	private LocalDate lastSanitizedDate;
	private double extraLuggageCharge;

	private List<SeatRequest> seats; // List of seats with seat type & gender restriction
}
