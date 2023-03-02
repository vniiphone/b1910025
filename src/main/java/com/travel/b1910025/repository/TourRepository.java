package com.travel.b1910025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

	@Query(value = "SELECT * FROM tour WHERE name LIKE BINARY CONCAT('%',:text,'%')", nativeQuery = true)
	List<Tour> findByNameLike(@Param("text") String text);

	boolean existsByCategoryId(Long id);

	// Kiểm tra tồn tại Hotel trong Tour
	Boolean existsByHotelId(Long id);

	// Kiểm tra tồn tại Restau trong Tour
	Boolean existsByRestauId(Long id);

	// Kiểm tra tồn tại Place trong Tour
	Boolean existsByPlaceId(Long id);

	// Kiểm tra tồn tại Firm trong Tour
	Boolean existsByFirmId(Long id);

}
