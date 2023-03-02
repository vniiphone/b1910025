package com.travel.b1910025.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @JsonIgnore
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	// @JsonIgnore
	@JoinColumn(name = "tour_id")
	@ManyToOne
	private Tour tour;

	@JoinColumn(name = "invoice_id")
	@ManyToOne
	private Invoice invoice;

	@NotNull
	@Min(0)
	private int quantity;

	@NotNull
	private int status;


	
	
	public CartItem(Long id, User user, Tour tour, Invoice invoice, @NotNull @Min(0) int quantity,
			@NotNull int status) {
		super();
		this.id = id;
		this.user = user;
		this.tour = tour;
		this.invoice = invoice;
		this.quantity = quantity;
		this.status = status;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(User user2, Tour tour2, int quantity2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	
	
}
