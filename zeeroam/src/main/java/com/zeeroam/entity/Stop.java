package com.zeeroam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stopName;
    private LocalTime stopTime;
    private Integer stopDuration; // Duration in minutes

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}
