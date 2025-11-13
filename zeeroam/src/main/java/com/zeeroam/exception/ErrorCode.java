package com.zeeroam.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	BUS_PROVIDER_EXISTS("E001", "Bus provider with this email already exists", HttpStatus.BAD_REQUEST),
	BUS_NUMBER_ALREADY_EXISTS("E002", "Bus with this number already exists", HttpStatus.BAD_REQUEST), 
	SEAT_NOT_BOOKED("E003", "Seat is not booked", HttpStatus.BAD_REQUEST),
	SEAT_ALREADY_BOOKED("E004", "Seat is already booked", HttpStatus.BAD_REQUEST),
	SEAT_NOT_FOUND("E005", "Seat not found", HttpStatus.BAD_REQUEST),
	BUS_PROVIDER_NOT_FOUND("E006", "Bus provider not found", HttpStatus.BAD_REQUEST), 
	BUS_NOT_FOUND("E006", "Bus provider not found", HttpStatus.BAD_REQUEST),
	ROUTE_NOT_FOUND("E006", "Bus provider not found", HttpStatus.BAD_REQUEST),
	BUS_ALREADY_ASSIGNED_TO_ROUTE("E006", "Bus provider not found", HttpStatus.BAD_REQUEST), 
	RIDE_NOT_FOUND("E006", "ride not found", HttpStatus.BAD_REQUEST), ;
	
	
	private final String code;
	private final String description;
	private final HttpStatus httpStatus;

	ErrorCode(String code, String description, HttpStatus httpStatus) {
		this.code = code;
		this.description = description;
		this.httpStatus = httpStatus;
	}
}
