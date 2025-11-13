package com.zeeroam.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StopDTO {

	private String stopName;
	
	private LocalTime stopTime;
	
	private double stopDuration;
	
}
