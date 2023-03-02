package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Tour;

public class TourResponse {
	@Autowired Tour tour;
	public Errors errors;
	public String message;
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public TourResponse(Tour tour, Errors errors, String message) {
		super();
		this.tour = tour;
		this.errors = errors;
		this.message = message;
	}
	
}
