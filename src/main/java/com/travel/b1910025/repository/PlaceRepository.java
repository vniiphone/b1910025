package com.travel.b1910025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	boolean existsById(Long id);

}