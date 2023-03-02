package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

// import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.travel.b1910025.models.Hotel;
import com.travel.b1910025.payload.request.HotelRequest;

@Component
public interface HotelService {

    Hotel createHotel(HotelRequest hotelRequest);

    Optional<Hotel> updateHotel(Long hotelId, HotelRequest hotelRequest);

    void deleteHotel(Long hoteldId);

    Hotel getASingleHotel(Long hoteldId);

    List<Hotel> getAllHotels();

}