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
import com.travel.b1910025.models.Place;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.payload.request.PlaceRequest;
import com.travel.b1910025.payload.response.ResourceNotFoundException;
import com.travel.b1910025.repository.PlaceRepository;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.TourRepository;

@CrossOrigin(origins = " http://localhost:3000")
@RestController
@RequestMapping("/api/place")
public class PlaceController {
	@Autowired
	private PlaceRepository placeRepo;
	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private AddressSecondRepository address2Repo;

	// get all firm
	@GetMapping("")
	public List<Place> getAlllPlace() {
		return placeRepo.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Place> createPlace(@Valid @RequestBody PlaceRequest placeReq) {
		try {
			AddressSecond add2 = address2Repo.findById(placeReq.getAddressSecond_id()).get();
			// firm.setAddress_id(add);
			Place a = new Place(placeReq.getName(), add2);
			return new ResponseEntity<>(placeRepo.save(a), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get firm by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Place> getEmployeeById(@PathVariable Long id) {
		Place place = placeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Place not exist with id :" + id));
		return ResponseEntity.ok(place);
	}

	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Place> updatePlace(@Valid @RequestBody PlaceRequest placeReq,
			@PathVariable("id") Long id) {
		try {
			Optional<Place> a = placeRepo.findById(id);

			if (a.isPresent()) {
				// Ward w = wardRepo.findById(address.getWard_id()).get();
				AddressSecond addres2 = address2Repo.findById(placeReq.getAddressSecond_id()).get();

				a.get().setName(placeReq.getName());
				a.get().setAddressSecond(addres2);
				return new ResponseEntity<>(placeRepo.save(a.get()), HttpStatus.OK);

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
	public ResponseEntity<String> deletePlace(@PathVariable("id") Long id) {
		boolean hasPlace = placeRepo.existsById(id);
		boolean hasExits = tourRepository.existsByPlaceId(id);

		if (hasPlace) {
			System.out.print("Id not exits");
			if (!hasExits) {
				placeRepo.deleteById(id);
				return new ResponseEntity<>("Delete", HttpStatus.BAD_REQUEST);
			} else {
				System.out.println("Ton tai Place trong tour");
				return new ResponseEntity<>("Still use in tour", HttpStatus.BAD_REQUEST);
			}
		} else if (!hasPlace) {
			System.out.println("Id not exits");
			return new ResponseEntity<>("ID " + id + " not found", HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
