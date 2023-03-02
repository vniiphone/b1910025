package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Hotel;

public class HotelResponse {
	@Autowired Hotel hotel;
	public Errors errors;
	public String message;
	

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
