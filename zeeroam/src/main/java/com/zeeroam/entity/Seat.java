package com.zeeroam.entity;

import com.zeeroam.util.SeatStatus;
import com.zeeroam.util.SeatType;
import com.zeeroam.util.GenderRestriction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;  

    @Enumerated(EnumType.STRING)
    private SeatType seatType; // SLEEPER, SEMI_SLEEPER, SEATER
    
    @Enumerated(EnumType.STRING)
    private SeatStatus status;  // Available, Booked, On Hold

    @Enumerated(EnumType.STRING)
    private GenderRestriction genderRestriction; // MALE_ONLY, FEMALE_ONLY, UNISEX

    private boolean isWindowSeat; // If the seat is a window seat

    private double price; // Price of the seat

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;  

}
