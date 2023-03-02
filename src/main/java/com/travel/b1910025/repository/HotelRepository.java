package com.travel.b1910025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	boolean existsById(Long id);

}