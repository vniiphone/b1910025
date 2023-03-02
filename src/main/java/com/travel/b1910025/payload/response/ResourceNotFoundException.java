package com.travel.b1910025.payload.response;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID 	= 1L;
	public ResourceNotFoundException(String message) {
		super(message);
	}
}

