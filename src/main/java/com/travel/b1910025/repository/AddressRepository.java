package com.travel.b1910025.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);

    List<Address> findByUser_Id(Long user_id);



}
