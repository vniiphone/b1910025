package com.travel.b1910025.payload.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CartItemRequest {
	@NotNull
	private Long user_id;
	@NotNull
	private Long tour_id;
	@NotNull
	@Min(0)
	private int quantity;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getTour_id() {
		return tour_id;
	}
	public void setTour_id(Long tour_id) {
		this.tour_id = tour_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	
}
