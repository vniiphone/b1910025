package com.travel.b1910025.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Place {

    // mô tả địa điểm trong tour

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", orphanRemoval = true)
    private List<Tour> tours = new ArrayList<>();

    // Mỗi Firm sẽ có 1 địa chỉ
    @JoinColumn(name = "addressSecond_id")
    @ManyToOne
    private AddressSecond addressSecond;

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

    public AddressSecond getAddressSecond() {
        return addressSecond;
    }

    public void setAddressSecond(AddressSecond addressSecond) {
        this.addressSecond = addressSecond;
    }

    public Place(@NotBlank(message = "Name is required") String name, AddressSecond addressSecond) {
        this.name = name;
        this.addressSecond = addressSecond;
    }

    public Place() {
    	
    }

}
