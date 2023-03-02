package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Place;

public class PlaceResponse {
	@Autowired Place place;
	public Errors errors;
	public String message;
	

	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
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
