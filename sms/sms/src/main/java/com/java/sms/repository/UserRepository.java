package com.java.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.sms.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
