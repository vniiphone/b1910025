package com.travel.b1910025.payload.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import com.travel.b1910025.models.Category;

public class CategoryResponse {
	@Autowired Category category;
	public Errors errors;
	public String message;
	

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
