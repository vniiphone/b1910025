package com.travel.b1910025.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
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
import org.springframework.web.bind.annotation.RestController;
import com.travel.b1910025.models.Restau;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.payload.request.RestauRequest;
import com.travel.b1910025.payload.response.ResourceNotFoundException;
import com.travel.b1910025.repository.RestauRepository;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.TourRepository;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/restau")
public class RestauController {
	@Autowired
	private RestauRepository restauRepo;
	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private AddressSecondRepository address2Repo;

	// get all firm
	@GetMapping("")
	public List<Restau> getAlllRestau() {
		return restauRepo.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Restau> createRestau(@Valid @RequestBody RestauRequest restau) {
		try {
			AddressSecond add2 = address2Repo.findById(restau.getAddressSecond_id()).get();
			// firm.setAddress_id(add);
			Restau a = new Restau(restau.getName(), add2);
			return new ResponseEntity<>(restauRepo.save(a), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get firm by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Restau> getEmployeeById(@PathVariable Long id) {
		Restau restau = restauRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant not exist with id :" + id));
		return ResponseEntity.ok(restau);
	}

	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Restau> updateRestau(@Valid @RequestBody RestauRequest restau,
			@PathVariable("id") Long id) {
		try {
			Optional<Restau> a = restauRepo.findById(id);

			if (a.isPresent()) {
				// Ward w = wardRepo.findById(address.getWard_id()).get();
				AddressSecond addres2 = address2Repo.findById(restau.getAddressSecond_id()).get();

				a.get().setName(restau.getName());
				a.get().setAddressSecond(addres2);
				return new ResponseEntity<>(restauRepo.save(a.get()), HttpStatus.OK);

			} else {
				throw new InvalidConfigurationPropertyValueException("restauId", id, "Not found");
			}
		} catch (Exception e) {
			// String a = "Loi";
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete employee rest api
	@DeleteMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<String> deleteRestau(@PathVariable("id") Long id) {
		boolean hasRestau = restauRepo.existsById(id);
		boolean hasExits = tourRepository.existsByRestauId(id);

		if (hasRestau) {
			System.out.print("Id not exits");
			if (!hasExits) {
				restauRepo.deleteById(id);
				return new ResponseEntity<>("Delete", HttpStatus.BAD_REQUEST);
			} else {
				System.out.println("Ton tai Restau trong tour");
				return new ResponseEntity<>("Still use in tour", HttpStatus.BAD_REQUEST);
			}
		} else if (!hasRestau) {
			System.out.println("Id not exits");
			return new ResponseEntity<>("ID " + id + " not found", HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
