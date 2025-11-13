package com.zeeroam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatPriceDTO {
    private Long seatId;
    private Long rideId;
    private double price;
}