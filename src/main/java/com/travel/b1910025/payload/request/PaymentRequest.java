package com.travel.b1910025.payload.request;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

	@NotNull
	private Long user_id;

	@NotNull
	private String paymentMethod;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

}
