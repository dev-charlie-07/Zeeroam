package com.zeeroam.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeroam.dto.RouteDTO;
import com.zeeroam.dto.StopDTO;
import com.zeeroam.entity.BusProvider;
import com.zeeroam.entity.Route;
import com.zeeroam.entity.Stop;
import com.zeeroam.exception.CustomException;
import com.zeeroam.exception.ErrorCode;
import com.zeeroam.repository.BusProviderRepository;
import com.zeeroam.repository.RouteRepository;
import com.zeeroam.repository.StopRepository;
import com.zeeroam.request.RouteRegisterRequest;

import jakarta.transaction.Transactional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private BusProviderRepository busProviderRepository;

    @Transactional
    public RouteDTO registerRouteWithStops(RouteRegisterRequest routeRequest) throws Exception {

    	BusProvider busProvider = busProviderRepository.findById(routeRequest.getBusProviderId())
    		    .orElseThrow(()->new CustomException(ErrorCode.BUS_PROVIDER_NOT_FOUND));

        // Create the route
        Route route = new Route();
        route.setRouteName(routeRequest.getRouteName());
        route.setDeparturePlace(routeRequest.getDeparturePlace());
        route.setArrivalPlace(routeRequest.getArrivalPlace());
        route.setDepartureTime(routeRequest.getDepartureTime());
        route.setArrivalTime(routeRequest.getArrivalTime());
        route.setDistance(routeRequest.getDistance());
        route.setRouteType(routeRequest.getRouteType());
        route.setSpecialInstructions(routeRequest.getSpecialInstructions());
        route.setBusProvider(busProvider);
        routeRepository.save(route);

        List<Stop> stops = routeRequest.getStops().stream()
                .map(stopRequest -> {
                    Stop stop = new Stop();
                    stop.setStopName(stopRequest.getStopName());
                    stop.setStopTime(stopRequest.getStopTime());
                    stop.setStopDuration(stopRequest.getStopDuration());
                    stop.setRoute(route);
                    return stop;
                }).collect(Collectors.toList());

        stopRepository.saveAll(stops);

        return new RouteDTO(route.getId(), route.getRouteName(), route.getDeparturePlace(),
                route.getArrivalPlace(), route.getDepartureTime().toString(), route.getArrivalTime().toString(),
                route.getDistance(), route.getRouteType(), route.getSpecialInstructions(),
                stops.stream().map(stop -> new StopDTO(stop.getStopName(), stop.getStopTime(), stop.getStopDuration()))
                    .collect(Collectors.toList()));
    }
    
    public RouteDTO getRouteById(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTE_NOT_FOUND));

        // Convert Stops to StopDTO
        List<StopDTO> stopDTOs = route.getStops().stream()
                .map(stop -> new StopDTO(
                        stop.getStopName(),
                        stop.getStopTime(),
                        stop.getStopDuration()
                ))
                .collect(Collectors.toList());

        // Convert Route to RouteDTO
        return new RouteDTO(
                route.getId(),
                route.getRouteName(),
                route.getDeparturePlace(),
                route.getArrivalPlace(),
                route.getDepartureTime().toString(),
                route.getArrivalTime().toString(),
                route.getDistance(),
                route.getRouteType(),
                route.getSpecialInstructions(),
                stopDTOs
        );
    }
    
    
}
