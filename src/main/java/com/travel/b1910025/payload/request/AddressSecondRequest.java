package com.travel.b1910025.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressSecondRequest {
	@NotNull
	private Long ward_id;

	@NotBlank
	private String addressSe;

	@NotBlank
	private String phone;

	public Long getWard_id() {
		return ward_id;
	}

	public void setWard_id(Long ward_id) {
		this.ward_id = ward_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressSe() {
		return addressSe;
	}

	public void setAddressSe(String addressSe) {
		this.addressSe = addressSe;
	}

}
