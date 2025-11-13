package com.zeeroam.request;

import com.zeeroam.util.GenderRestriction;
import com.zeeroam.util.SeatType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SeatRequest {
    private String seatNumber; // E.g., 1A, 1B, etc.
    private SeatType seatType; // E.g., SEATER, SLEEPER, etc.
    private boolean windowSeat; // E.g., true/false
    private GenderRestriction genderRestriction; // MALE_ONLY, FEMALE_ONLY, UNISEX
}