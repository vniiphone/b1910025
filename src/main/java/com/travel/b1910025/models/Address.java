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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String phone;

    @JoinColumn(name = "ward_id")
    @ManyToOne
    private Ward ward;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    // Thuộc tính user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address(String address, String phone, Ward ward, User user) {
		super();
		this.address = address;
		this.phone = phone;
		this.ward = ward;
		this.user = user;
	}
	//new Address(address.getAddress(), address.getPhone(),ward, firm ,restau,hotel,place, user)
//
//	public Address(String address2, String phone2, Long firm_id, Long hotel_id, Long restau_id, Long place_id, Ward w,
//			User u) {
//		// TODO Auto-generated constructor stub
//	}

	public Address() {
	
		// TODO Auto-generated constructor stub
	}

	
	

    
}
