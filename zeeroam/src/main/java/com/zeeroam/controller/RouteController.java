package com.zeeroam.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeeroam.dto.BusRouteAssignmentDTO;
import com.zeeroam.dto.RouteDTO;
import com.zeeroam.entity.BusRouteAssignment;
import com.zeeroam.repository.BusRouteAssignmentRepository;
import com.zeeroam.request.BusRouteAssignmentRequest;
import com.zeeroam.request.RouteRegisterRequest;
import com.zeeroam.service.BusRouteAssignmentService;
import com.zeeroam.service.RouteService;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusRouteAssignmentService busRouteAssignmentService;
    
    @Autowired
    private BusRouteAssignmentRepository busRouteAssignmentRepository;
    
    // Endpoint to create a route
    @PostMapping("/register")
    public ResponseEntity<String> createRoute(@RequestBody RouteRegisterRequest routeRegisterRequest) {
        try {
            @SuppressWarnings("unused")
			RouteDTO route = routeService.registerRouteWithStops(routeRegisterRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Route created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create route: " + e.getMessage());
        }
    }
    
    // Assign a bus to a route
    @PostMapping("/assign/bus")
    public ResponseEntity<String> assignBusToRoute(@RequestBody BusRouteAssignmentRequest request) {
        busRouteAssignmentService.assignBusToRoute(request);
        return ResponseEntity.ok("Bus assigned successfully to the route");
    }
    
    @GetMapping("/{routeId}/buses")
    public ResponseEntity<List<BusRouteAssignmentDTO>> getBusesForRoute(@PathVariable Long routeId) {
        List<BusRouteAssignment> assignments = busRouteAssignmentRepository.findByRouteId(routeId);
        
        // Convert to DTO
        List<BusRouteAssignmentDTO> assignmentDTOs = assignments.stream()
                .map(assignment -> new BusRouteAssignmentDTO(
                        assignment.getBus().getId(),
                        assignment.getBus().getBusNumber(),
                        assignment.getBus().getBusType(),
                        assignment.getRoute().getId(),
                        assignment.getRoute().getRouteName(),
                        assignment.getAssignmentDate()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(assignmentDTOs);
    }
    
    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long routeId) {
        RouteDTO routeDTO = routeService.getRouteById(routeId);
        return ResponseEntity.ok(routeDTO);
    }
}
