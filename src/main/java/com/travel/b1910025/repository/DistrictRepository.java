package com.travel.b1910025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findAllByCity_Id(Long city_id);
}
