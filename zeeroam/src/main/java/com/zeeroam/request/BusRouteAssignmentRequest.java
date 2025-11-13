package com.zeeroam.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRouteAssignmentRequest {
    private Long busId;
    private Long routeId;
    private LocalDateTime departureTime;
}