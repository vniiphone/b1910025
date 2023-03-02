package com.travel.b1910025.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.b1910025.models.Tour;
import com.travel.b1910025.repository.TourRepository;

@Service
public class ProductSearchSerivceImpl implements ProductSearchService{
	@Autowired
	TourRepository tourRepo;
	
	@Override
	public List<Tour> searchTourByName(String text) {
		// TODO Auto-generated method stub
		
		return tourRepo.findByNameLike(text);
	}
}
