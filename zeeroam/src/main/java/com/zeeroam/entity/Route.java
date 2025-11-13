package com.zeeroam.entity;

import java.time.LocalTime;
import java.util.List;

import com.zeeroam.util.RouteType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String departurePlace;
    private String arrivalPlace;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Double distance;
    private String specialInstructions;

    @Enumerated(EnumType.STRING) // Store as a string in DB
    private RouteType routeType; // FIX: Added this field

    @ManyToOne
    @JoinColumn(name = "bus_provider_id", nullable = false)
    private BusProvider busProvider;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Stop> stops;
}
