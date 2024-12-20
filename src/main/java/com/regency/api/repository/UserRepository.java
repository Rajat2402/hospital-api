package com.regency.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.regency.api.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsernameOrEmail(String username , String email);

	public boolean existsByUsername(String username);

}
