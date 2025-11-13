package com.zeeroam.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusRouteAssignmentDTO {
    private Long busId;
    private String busNumber;
    private String busType;
    private Long routeId;
    private String routeName;
    private LocalDateTime departureTime;
}
