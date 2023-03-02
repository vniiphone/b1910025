package com.travel.b1910025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsById(Long id);
}
