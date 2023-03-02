package com.travel.b1910025.payload.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TourRequest {
	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Description is required")
	private String description;

	@NotNull
	@Min(0)
	@Max(500)
	private int slot;

	@NotNull
	@Min(0)
	private int price;

	@NotNull
	private Date beginTrip;

	@NotNull
	private Date endTrip;

	@NotBlank(message = "Image is required")
	private String imageUrl;

	@NotBlank(message = "Image is required")
	private String imagePublicId;

	@NotNull
	private Long category_id;

	@NotNull
	private Long firm_id;

	@NotNull
	private Long place_id;

	@NotNull
	private Long hotel_id;

	@NotNull
	private Long restau_id;
	
	

	public TourRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TourRequest(@NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Description is required") String description, @NotNull @Min(0) @Max(500) int slot,
			@NotNull @Min(0) int price, @NotNull Date beginTrip, @NotNull Date endTrip,
			@NotBlank(message = "Image is required") String imageUrl,
			@NotBlank(message = "Image is required") String imagePublicId, @NotNull Long category_id,
			@NotNull Long firm_id, @NotNull Long place_id, @NotNull Long hotel_id, @NotNull Long restau_id) {
		super();
		this.name = name;
		this.description = description;
		this.slot = slot;
		this.price = price;
		this.beginTrip = beginTrip;
		this.endTrip = endTrip;
		this.imageUrl = imageUrl;
		this.imagePublicId = imagePublicId;
		this.category_id = category_id;
		this.firm_id = firm_id;
		this.place_id = place_id;
		this.hotel_id = hotel_id;
		this.restau_id = restau_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSlot() {
		return this.slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getBeginTrip() {
		return this.beginTrip;
	}

	public void setBeginTrip(Date beginTrip) {
		this.beginTrip = beginTrip;
	}

	public Date getEndTrip() {
		return this.endTrip;
	}

	public void setEndTrip(Date endTrip) {
		this.endTrip = endTrip;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImagePublicId() {
		return this.imagePublicId;
	}

	public void setImagePublicId(String imagePublicId) {
		this.imagePublicId = imagePublicId;
	}

	public Long getCategory_id() {
		return this.category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getFirm_id() {
		return this.firm_id;
	}

	public void setFirm_id(Long firm_id) {
		this.firm_id = firm_id;
	}

	public Long getPlace_id() {
		return this.place_id;
	}

	public void setPlace_id(Long place_id) {
		this.place_id = place_id;
	}

	public Long getHotel_id() {
		return this.hotel_id;
	}

	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Long getRestau_id() {
		return this.restau_id;
	}

	public void setRestau_id(Long restau_id) {
		this.restau_id = restau_id;
	}

}
