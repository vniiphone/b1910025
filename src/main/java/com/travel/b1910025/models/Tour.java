package com.travel.b1910025.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

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

	@NotBlank(message = "Description is required")
	private String description;

	@NotBlank(message = "Image is required")
	private String imageUrl;

	@NotBlank(message = "Image is required")
	private String imagePublicId;

	// @JsonIgnore
	@JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;

	// @JsonIgnore
	@JoinColumn(name = "firm_id")
	@ManyToOne
	private Firm firm;

	// @JsonIgnore
	@JoinColumn(name = "place_id")
	@ManyToOne
	private Place place;

	// @JsonIgnore
	@JoinColumn(name = "hotel_id")
	@ManyToOne
	private Hotel hotel;

	// @JsonIgnore
	@JoinColumn(name = "restau_id")
	@ManyToOne
	private Restau restau;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getBeginTrip() {
		return beginTrip;
	}

	public void setBeginTrip(Date beginTrip) {
		this.beginTrip = beginTrip;
	}

	public Date getEndTrip() {
		return endTrip;
	}

	public void setEndTrip(Date endTrip) {
		this.endTrip = endTrip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImagePublicId() {
		return imagePublicId;
	}

	public void setImagePublicId(String imagePublicId) {
		this.imagePublicId = imagePublicId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Restau getRestau() {
		return restau;
	}

	public void setRestau(Restau restau) {
		this.restau = restau;
	}

	public Tour(Long id, @NotBlank(message = "Name is required") String name, @NotNull @Min(0) @Max(500) int slot,
			@NotNull @Min(0) int price, @NotNull Date beginTrip, @NotNull Date endTrip,
			@NotBlank(message = "Description is required") String description,
			@NotBlank(message = "Image is required") String imageUrl,
			@NotBlank(message = "Image is required") String imagePublicId, Category category, Firm firm, Place place,
			Hotel hotel, Restau restau) {
		super();
		this.id = id;
		this.name = name;
		this.slot = slot;
		this.price = price;
		this.beginTrip = beginTrip;
		this.endTrip = endTrip;
		this.description = description;
		this.imageUrl = imageUrl;
		this.imagePublicId = imagePublicId;
		this.category = category;
		this.firm = firm;
		this.place = place;
		this.hotel = hotel;
		this.restau = restau;
	}
	
	
}
