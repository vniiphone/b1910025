package com.travel.b1910025.security.services;

import java.util.List;


import org.springframework.stereotype.Component;

import com.travel.b1910025.models.Tour;

@Component
public interface ProductSearchService {
	List<Tour> searchTourByName(String text); 
}
