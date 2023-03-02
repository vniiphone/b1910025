package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Firm;

public class FirmResponse {
	@Autowired Firm firm;
	public Errors errors;
	public String message;
	
	

	public Firm getFirm() {
		return this.firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
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
