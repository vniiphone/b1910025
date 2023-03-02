package com.travel.b1910025.controllers;

import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.travel.b1910025.models.Firm;
import com.travel.b1910025.models.Address;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.models.Category;
import com.travel.b1910025.models.Tour;
import com.travel.b1910025.models.User;
import com.travel.b1910025.models.Ward;
import com.travel.b1910025.payload.request.AddressRequest;
import com.travel.b1910025.payload.request.FirmRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.payload.response.ResourceNotFoundException;
import com.travel.b1910025.repository.FirmRepository;
import com.travel.b1910025.repository.AddressRepository;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.TourRepository;
import com.travel.b1910025.repository.WardRepository;
import com.travel.b1910025.security.services.FirmService;
import com.travel.b1910025.security.services.TourService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/firm")
public class FirmController {
	@Autowired
	private FirmRepository firmRepository;
	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private AddressSecondRepository address2Repo;

	// get all firm
	@GetMapping("")
	public List<Firm> getAlllFirm() {
		return firmRepository.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Firm> createFirm(@Valid @RequestBody FirmRequest firm) {
		try {
			AddressSecond add2 = address2Repo.findById(firm.getAddressSecond_id()).get();
			// firm.setAddress_id(add);
			Firm a = new Firm(firm.getName(), add2);
			return new ResponseEntity<>(firmRepository.save(a), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get firm by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Firm> getEmployeeById(@PathVariable Long id) {
		Firm firm = firmRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
		return ResponseEntity.ok(firm);
	}

	// update firm rest api

	// @PutMapping("/{id}")
	// public ResponseEntity<Firm> updateCategory(@PathVariable Long id,
	// @RequestBody Firm firmDetails) {
	// Firm firm = firmRepository.findById(id)
	// .orElseThrow(() -> new ResourceNotFoundException("Firm not exist with id :" +
	// id));
	// firm.setName(firmDetails.getName());
	// Address addres = addressRepo.findById(firm.getAddress_id()).get();
	//// Firm a = new Firm( firm.getName(),add);
	//// return new ResponseEntity<>(firmRepository.save(a), HttpStatus.CREATED);
	//
	// Firm updatedEmployee = firmRepository.save(firm);
	// return ResponseEntity.ok(updatedEmployee);
	// }

	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Firm> updateFirm(@Valid @RequestBody FirmRequest firm,
			@PathVariable("id") Long id) {
		try {
			Optional<Firm> a = firmRepository.findById(id);

			if (a.isPresent()) {
				// Ward w = wardRepo.findById(address.getWard_id()).get();
				AddressSecond addres2 = address2Repo.findById(firm.getAddressSecond_id()).get();

				a.get().setName(firm.getName());
				a.get().setAddressSecond(addres2);
				return new ResponseEntity<>(firmRepository.save(a.get()), HttpStatus.OK);

			} else {
				throw new InvalidConfigurationPropertyValueException("firmId", id, "Not found");
			}
		} catch (Exception e) {
			// String a = "Loi";
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete employee rest api
	@DeleteMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<String> deleteFirm(@PathVariable("id") Long id) {
		boolean hasFirm = firmRepository.existsById(id);
		boolean hasExits = tourRepository.existsByFirmId(id);

		if (hasFirm) {
			System.out.print("Id not exits");
			if (!hasExits) {
				firmRepository.deleteById(id);
				return new ResponseEntity<>("Delete", HttpStatus.BAD_REQUEST);
			} else {
				System.out.println("Ton tai Firm trong tour");
				return new ResponseEntity<>("Still use in tour", HttpStatus.BAD_REQUEST);
			}
		} else if (!hasFirm) {
			System.out.println("Id not exits");
			return new ResponseEntity<>("ID " + id + " not found", HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
