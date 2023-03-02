package com.travel.b1910025.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AddressSecond {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String address2;

	private String phone;

	@JoinColumn(name = "ward_id")
	@ManyToOne
	private Ward ward;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}


	public AddressSecond() {

		// TODO Auto-generated constructor stub
	}

	public AddressSecond(String address2, String phone, Ward ward) {
		this.address2 = address2;
		this.phone = phone;
		this.ward = ward;
	}

}
