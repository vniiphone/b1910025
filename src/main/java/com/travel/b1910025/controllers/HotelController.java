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
import com.travel.b1910025.models.Hotel;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.payload.request.HotelRequest;
import com.travel.b1910025.payload.response.ResourceNotFoundException;
import com.travel.b1910025.repository.HotelRepository;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.TourRepository;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/hotel")
public class HotelController {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private AddressSecondRepository address2Repo;

	// get all firm
	@GetMapping("")
	public List<Hotel> getAlllHotel() {
		return hotelRepository.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Hotel> createHotel(@Valid @RequestBody HotelRequest hotel) {
		try {
			AddressSecond add2 = address2Repo.findById(hotel.getAddressSecond_id()).get();
			// firm.setAddress_id(add);
			Hotel a = new Hotel(hotel.getName(), add2);
			return new ResponseEntity<>(hotelRepository.save(a), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get firm by id rest api
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getEmployeeById(@PathVariable Long id) {
		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id :" + id));
		return ResponseEntity.ok(hotel);
	}



	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody HotelRequest hotel,
			@PathVariable("id") Long id) {
		try {
			Optional<Hotel> a = hotelRepository.findById(id);

			if (a.isPresent()) {
				// Ward w = wardRepo.findById(address.getWard_id()).get();
				AddressSecond addres2 = address2Repo.findById(hotel.getAddressSecond_id()).get();

				a.get().setName(hotel.getName());
				a.get().setAddressSecond(addres2);
				return new ResponseEntity<>(hotelRepository.save(a.get()), HttpStatus.OK);

			} else {
				throw new InvalidConfigurationPropertyValueException("hotelId", id, "Not found");
			}
		} catch (Exception e) {
			// String a = "Loi";
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete employee rest api
	@DeleteMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) {
		boolean hasHotel = hotelRepository.existsById(id);
		boolean hasExits = tourRepository.existsByHotelId(id);

		if (hasHotel) {
			System.out.print("Id not exits");
			if (!hasExits) {
				hotelRepository.deleteById(id);
				return new ResponseEntity<>("Delete", HttpStatus.BAD_REQUEST);
			} else {
				System.out.println("Ton tai Hotel trong tour");
				return new ResponseEntity<>("Still use in tour", HttpStatus.BAD_REQUEST);
			}
		} else if (!hasHotel) {
			System.out.println("Id not exits");
			return new ResponseEntity<>("ID " + id + " not found", HttpStatus.NOT_FOUND);
		} else {
			System.out.println("Lỗi 500 INTERNAL SERVER ERRROR");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
