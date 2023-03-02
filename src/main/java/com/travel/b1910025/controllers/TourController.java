package com.travel.b1910025.controllers;

import java.util.ArrayList;
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

import com.travel.b1910025.models.Tour;
import com.travel.b1910025.payload.request.TourRequest;
import com.travel.b1910025.payload.response.MessageResponse;
import com.travel.b1910025.security.services.TourService;

@CrossOrigin(origins = "http://127.0.0.1:5173", maxAge = 3600)
@RestController
@RequestMapping("/api/tour")
public class TourController {
    @Autowired
    TourService tourService;

    @GetMapping("")
    public ResponseEntity<Page<Tour>> getAllTours(@RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        try {
            Page<Tour> tours = tourService.getAllTours(page, sortBy);
            return new ResponseEntity<>(tours, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable("id") Long id) {
        Tour tour = tourService.getASingleTour(id);
        return new ResponseEntity<>(tour, HttpStatus.OK);

    }

    @PostMapping(value = "/create", consumes = { "*/*" })
    public ResponseEntity<Tour> createTour(@Valid @RequestBody TourRequest tour) {
        return new ResponseEntity<>(tourService.createTour(tour), HttpStatus.CREATED);
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
