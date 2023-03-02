package com.travel.b1910025.security.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.TourRequest;

@Component
public interface TourService {
	Tour createTour(TourRequest tourRequest);

    Optional<Tour> updateTour(Long tourId, TourRequest tourRequest);

    void deleteTour(Long tourId);

    Tour getASingleTour(Long tourId);

    Page<Tour> getAllTours(Optional<Integer> page, Optional<String> sortBy);
}