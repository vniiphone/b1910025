package com.travel.b1910025.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.b1910025.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByRole(String role);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

}
