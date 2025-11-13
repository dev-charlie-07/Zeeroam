package com.zeeroam.request;

import com.zeeroam.util.RouteType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RouteRegisterRequest {
    private Long busProviderId;
    private String routeName;
    private String departurePlace;
    private String arrivalPlace;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double distance;
    private String specialInstructions;
    private RouteType routeType; // FIX: Added this field
    private List<StopRequest> stops;

    @Getter
    @Setter
    public static class StopRequest {
        private String stopName;
        private LocalTime stopTime;
        private Integer stopDuration;
    }
}
