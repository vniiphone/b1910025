package com.travel.b1910025.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.travel.b1910025.models.Firm;
import com.travel.b1910025.models.Hotel;
import com.travel.b1910025.models.Restau;

import com.travel.b1910025.models.Place;
import com.travel.b1910025.models.Category;
import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.TourRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.payload.response.TourResponse;
import com.travel.b1910025.repository.FirmRepository;
import com.travel.b1910025.repository.HotelRepository;
import com.travel.b1910025.repository.PlaceRepository;
import com.travel.b1910025.repository.RestauRepository;
import com.travel.b1910025.repository.CategoryRepository;
import com.travel.b1910025.repository.TourRepository;
import org.springframework.data.domain.Sort;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    FirmRepository firmRepo;
    @Autowired
    TourRepository tourRepo;
    @Autowired
    HotelRepository hotelRepo;
    @Autowired
    RestauRepository restauRepo;
    @Autowired
    PlaceRepository placeRepo;

    @Override
    public Tour createTour(TourRequest tourRequest) {
        Category category = categoryRepo.findById(tourRequest.getCategory_id()).orElseThrow();
        Firm firm = firmRepo.findById(tourRequest.getFirm_id()).orElseThrow();
        Hotel hotel = hotelRepo.findById(tourRequest.getFirm_id()).orElseThrow();
        Restau restau = restauRepo.findById(tourRequest.getFirm_id()).orElseThrow();
        Place place = placeRepo.findById(tourRequest.getFirm_id()).orElseThrow();
        Tour tour = new Tour(tourRequest.getName(), tourRequest.getDescription(),
                tourRequest.getSlot(), tourRequest.getPrice(), tourRequest.getBeginTrip().toString(),
                tourRequest.getEndTrip().toString(),
                tourRequest.getImageUrl(), tourRequest.getImagePublicId(), category, firm, hotel, restau, place);
        // Thiếu thuộc tính của Tour khi thêm

        return tourRepo.save(tour);
    }

    @Override
    public Optional<Tour> updateTour(Long tourId, TourRequest tourRequest) {
        // TODO Auto-generated method stub
        Optional<Tour> tour = tourRepo.findById(tourId);
        if (tour.isPresent()) {
            // Thiếu thuộc tính khi sửa
            Category category = categoryRepo.findById(tourRequest.getCategory_id()).orElseThrow();
            Firm firm = firmRepo.findById(tourRequest.getFirm_id()).orElseThrow();
            Hotel hotel = hotelRepo.findById(tourRequest.getFirm_id()).orElseThrow();
            Restau restau = restauRepo.findById(tourRequest.getFirm_id()).orElseThrow();
            Place place = placeRepo.findById(tourRequest.getFirm_id()).orElseThrow();

            tour.get().setName(tourRequest.getName());
            tour.get().setDescription(tourRequest.getDescription());
            tour.get().setPrice(tourRequest.getPrice());
            tour.get().setSlot(tourRequest.getSlot());
            tour.get().setBeginTrip(tourRequest.getBeginTrip());
            tour.get().setEndTrip(tourRequest.getEndTrip());
            tour.get().setImageUrl(tourRequest.getImageUrl());
            tour.get().setImagePublicId(tourRequest.getImagePublicId());
            tour.get().setCategory(category);
            tour.get().setFirm(firm);
            tour.get().setHotel(hotel);
            tour.get().setRestau(restau);
            tour.get().setPlace(place);
            tourRepo.save(tour.get());
            return tour;
        } else {
            throw new InvalidConfigurationPropertyValueException("tourId", tourId, "Not found");
        }

    }

    @Override
    public void deleteTour(Long tourId) {
        // TODO Auto-generated method stub
        if (tourRepo.findById(tourId).get().getId().equals(tourId)) {
            tourRepo.deleteById(tourId);
        } else
            throw new InvalidConfigurationPropertyValueException("tourId", tourId, "Not found");
    }

    @Override
    public Tour getASingleTour(Long tourId) {
        // TODO Auto-generated method stub
        return tourRepo.findById(tourId)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("tourId", tourId, "Not found"));
    }

    @Override
    public Page<Tour> getAllTours(Optional<Integer> page, Optional<String> sortBy) {
        // TODO Auto-generated method stub
        return tourRepo.findAll(PageRequest.of(
                page.orElse(0),
                20,
                Sort.Direction.ASC, sortBy.orElse("id")));
    }

}
