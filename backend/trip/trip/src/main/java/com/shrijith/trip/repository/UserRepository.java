package com.shrijith.trip.repository;

import com.shrijith.trip.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}