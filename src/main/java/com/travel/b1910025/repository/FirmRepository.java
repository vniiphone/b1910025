package com.travel.b1910025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Long> {
	boolean existsById(Long id);

}