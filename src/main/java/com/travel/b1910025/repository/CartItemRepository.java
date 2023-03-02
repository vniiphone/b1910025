package com.travel.b1910025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.CartItem;;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser_Id(long userId);

	CartItem findByUser_IdAndTour_IdAndStatus(long user_id, long tour_id, int status);
}
