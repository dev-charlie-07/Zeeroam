package com.zeeroam.entity;

import java.util.List;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zeeroam.util.BusCategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;  

    private String busType;    

    private BusCategory busCategory; 

    private int floors; 

    private int totalSeats; 

    private boolean hasWifi; 

    private boolean hasChargingPoints; 

    private boolean hasRecliningSeats; 

    private boolean hasMealService; 

    private boolean hasBlanket;  // If blankets are provided

    private boolean hasPillow;  // If pillows are provided

    private LocalDate lastSanitizedDate; 

    private double extraLuggageCharge; 

    @ManyToOne
    @JoinColumn(name = "bus_provider_id")
    @JsonManagedReference  
    private BusProvider busProvider; 

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Seat> seats; 

}
