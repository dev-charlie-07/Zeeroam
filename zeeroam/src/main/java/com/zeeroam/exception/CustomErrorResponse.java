package com.zeeroam.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	private String path;
	
	private String responseCode;
	private String responseMessage;
	
	@Override
	public String toString() {
	    return "CustomErrorResponse{" +
	            "timestamp=" + timestamp +
	            ", responseMessage='" + responseMessage + '\'' +
	            ", responseCode='" + responseCode + '\'' +
	            ", path='" + path + '\'' +
	            '}';
	}
}
