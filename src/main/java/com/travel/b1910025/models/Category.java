package com.travel.b1910025.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true)
	private List<Tour> tours = new ArrayList<>();

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

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	public Category(Long id, @NotBlank(message = "Name is required") String name, List<Tour> tours) {
		super();
		this.id = id;
		this.name = name;
		this.tours = tours;
	}

}
