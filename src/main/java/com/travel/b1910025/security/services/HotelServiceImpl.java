package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import com.travel.b1910025.models.Address;
import com.travel.b1910025.models.AddressSecond;
import com.travel.b1910025.models.Hotel;
// import com.travel.b1910025.models.Category;
import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.HotelRequest;
import com.travel.b1910025.payload.request.TourRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.repository.AddressSecondRepository;
import com.travel.b1910025.repository.HotelRepository;
// import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.TourRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepo;
	@Autowired
	TourRepository tourRepo;
	@Autowired
	AddressSecondRepository addressSecondRepo;

	@Override
	public Hotel createHotel(HotelRequest hotelRequest) {
		// Product product =
		// productRepo.findById(brandRequest.getProduct_id()).orElseThrow();
		AddressSecond addressSecond = addressSecondRepo.findById(hotelRequest.getAddressSecond_id()).orElseThrow();
		Hotel hotel = new Hotel(hotelRequest.getName(), addressSecond);
		return hotelRepo.save(hotel);
	}

	@Override
	public Optional<Hotel> updateHotel(Long hotelId, HotelRequest hotelRequest) {
		// TODO Auto-generated method stub
		Optional<Hotel> hotel = hotelRepo.findById(hotelId);
		if (hotel.isPresent()) {

			hotel.get().setName(hotelRequest.getName());

			hotelRepo.save(hotel.get());
			return hotel;
		} else {
			throw new InvalidConfigurationPropertyValueException("hotelId", hotelId, "hotelId");
		}

	}

	private List<Tour> getAllTours() {
		return tourRepo.findAll();
	}

	@Override
	public void deleteHotel(Long hotelId) {

		boolean hasExist = hotelRepo.existsById(hotelId);

		// if (brandRepo.findById(brandId).get().getId().equals(brandId))
		if (hasExist) {
			hotelRepo.deleteById(hotelId);
		} else
			throw new InvalidConfigurationPropertyValueException("hotelId", hotelId, "Not Found");

	}

	@Override
	public Hotel getASingleHotel(Long hotelId) {
		// TODO Auto-generated method stub
		return hotelRepo.findById(hotelId)
				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("hotelId", hotelId, "Not found"));
	}

	@Override
	public List<Hotel> getAllHotels() {
		// TODO Auto-generated method stub
		return hotelRepo.findAll();
	}

}