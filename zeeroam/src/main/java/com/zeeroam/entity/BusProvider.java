package com.zeeroam.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BusProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String contact; // Contact details of the bus provider
	private String email; // Email address

	// Any other relevant fields for a bus provider

	@OneToMany(mappedBy = "busProvider", cascade = CascadeType.ALL)
	@JsonBackReference  // Prevents circular reference
	private List<Bus> buses;
}
