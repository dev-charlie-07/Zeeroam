package com.zeeroam.dto;

import com.zeeroam.util.SeatStatus;
import com.zeeroam.util.SeatType;
import com.zeeroam.util.GenderRestriction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeatDTO {
    private String seatNumber;
    private SeatStatus status;
    private SeatType seatType;
    private boolean isWindowSeat;
    private GenderRestriction genderRestriction;
}
