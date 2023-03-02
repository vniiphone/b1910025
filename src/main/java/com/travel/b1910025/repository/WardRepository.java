package com.travel.b1910025.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findAllByDistrict_Id(Long district_id);
}
