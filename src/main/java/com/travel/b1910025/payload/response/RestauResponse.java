package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Restau;

public class RestauResponse {
	@Autowired Restau restau;
	public Errors errors;
	public String message;
	
	

	public Restau getRestau() {
		return this.restau;
	}

	public void setRestau(Restau restau) {
		this.restau = restau;
	}

	public Errors getErrors() {
		return this.errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
