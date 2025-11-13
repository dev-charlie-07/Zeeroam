package com.zeeroam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeroam.entity.Bus;
import com.zeeroam.entity.BusRouteAssignment;
import com.zeeroam.entity.Route;
import com.zeeroam.exception.CustomException;
import com.zeeroam.exception.ErrorCode;
import com.zeeroam.repository.BusRepository;
import com.zeeroam.repository.BusRouteAssignmentRepository;
import com.zeeroam.repository.RouteRepository;
import com.zeeroam.request.BusRouteAssignmentRequest;

import jakarta.transaction.Transactional;

@Service
public class BusRouteAssignmentService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusRouteAssignmentRepository busRouteAssignmentRepository;

    @Transactional
    public void assignBusToRoute(BusRouteAssignmentRequest request) {
        // Fetch Bus
        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new CustomException(ErrorCode.BUS_NOT_FOUND));

        // Fetch Route
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new CustomException(ErrorCode.ROUTE_NOT_FOUND));

        // Check if the bus is already assigned to this route at the same time
        boolean alreadyAssigned = busRouteAssignmentRepository
                .findByBusIdAndRouteIdAndAssignmentDate(request.getBusId(), request.getRouteId(), request.getDepartureTime())
                .isPresent();

        if (alreadyAssigned) {
            throw new CustomException(ErrorCode.BUS_ALREADY_ASSIGNED_TO_ROUTE);
        }

        // Assign Bus to Route with Departure Time
        BusRouteAssignment assignment = new BusRouteAssignment();
        assignment.setBus(bus);
        assignment.setRoute(route);
        assignment.setAssignmentDate(request.getDepartureTime());

        busRouteAssignmentRepository.save(assignment);
    }
}

