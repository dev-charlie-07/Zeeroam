package com.zeeroam.exception;


import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;
	
	private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public CustomException(String string) {
		this.errorCode = null;
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
        return errorCode.getCode();
    }

    public String getDescription() {
        return errorCode.getDescription();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
