package com.zeeroam.dto;

import java.util.List;

import com.zeeroam.util.RouteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
	private Long id;
    private String routeName;
    private String departurePlace;
    private String arrivalPlace;
    private String departureTime;
    private String arrivalTime;
    private double distance;
    private RouteType routeType;
    private String specialInstructions;
    private List<StopDTO> stops;
}
