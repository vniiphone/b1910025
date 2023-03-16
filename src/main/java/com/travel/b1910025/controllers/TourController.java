package com.travel.b1910025.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.models.Category;
import com.travel.b1910025.models.Firm;
import com.travel.b1910025.models.Hotel;
import com.travel.b1910025.models.Place;
import com.travel.b1910025.models.Restau;
import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.TourRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.FirmRepository;
import com.travel.b1910025.repository.HotelRepository;
import com.travel.b1910025.repository.PlaceRepository;
import com.travel.b1910025.repository.RestauRepository;
import com.travel.b1910025.repository.TourRepository;
import com.travel.b1910025.security.services.TourService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/tour")
public class TourController {
	@Autowired
	TourService tourService;
	@Autowired
	TourRepository tourRp;
	@Autowired
	RestauRepository restauRp;
	@Autowired
	HotelRepository hoteRp;
	@Autowired
	FirmRepository firmRp;
	@Autowired
	PlaceRepository placeRp;
	@Autowired
	CategoryRepository cateRp;

	@GetMapping("")
	public ResponseEntity<Page<Tour>> getAllTours(@RequestParam Optional<Integer> page,
			@RequestParam Optional<String> sortBy) {
		try {
			Page<Tour> tours = tourService.getAllTours(page, sortBy);
			return new ResponseEntity<>(tours, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tour> getTourById(@PathVariable("id") Long id) {
		Tour tour = tourService.getASingleTour(id);
		return new ResponseEntity<>(tour, HttpStatus.OK);

	}
	// định dạng: yyyy/mm/dd: -->ex: 2023/02/24
	@PostMapping(value = "/create", consumes = { "*/*" })
	public ResponseEntity<Tour> createTour(@RequestParam("name") String name, @RequestParam("slot") int slot,
			@RequestParam("price") int price, @RequestParam("beginTrip") Date beginTrip,
			@RequestParam("endTrip") Date endTrip, @RequestParam("description") String description,
			@RequestParam("imageUrl") String imageUrl, @RequestParam("imagePublicId") String imagePublicId,
			@RequestParam("category_id") Long category_id, @RequestParam("firm_id") Long firm_id,
			@RequestParam("place_id") Long place_id, @RequestParam("hotel_id") Long hotel_id,
			@RequestParam("restau_id") Long restau_id) {
		Hotel hotel = new Hotel();
		Firm firm = new Firm();
		Restau restau = new Restau();
		Category category = new Category();
		Place place = new Place();
		
//		String nameOfHotel = hoteRp.findById(hotel_id).get().getName().toString();
//		hotel.setName(nameOfHotel);
		try {
			hotel.setId(hotel_id);
			firm.setId(firm_id);
			restau.setId(restau_id);
			category.setId(category_id);
			place.setId(place_id);
			Tour a = new Tour(name, slot, price, beginTrip, endTrip, description, imageUrl, imagePublicId, category,
					firm, place, hotel, restau);
			return new ResponseEntity<>(tourRp.save(a), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//    	return new ResponseEntity<>(tourService.createTour(Tour), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = { "*/*" })
	public ResponseEntity<Optional<Tour>> updateTour(@PathVariable("id") Long id,
			@RequestBody @Valid TourRequest tour) {
		return new ResponseEntity<>(tourService.updateTour(id, tour), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageResponse> deleteTour(@PathVariable("id") Long id) {
		try {
			tourService.deleteTour(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
